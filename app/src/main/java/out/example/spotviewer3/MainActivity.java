package out.example.spotviewer3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvParser;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.LongUnaryOperator;

public class MainActivity extends AppCompatActivity {

//    private AppBarConfiguration mAppBarConfiguration;

    private List<Shop> shops = new ArrayList<>();

    private List<Shop> filtered = new ArrayList<>();

    private GoogleMap map;

    private Button menuOpen;
    private EditText search;
    private ImageView searchButton;
    private DrawerLayout layout;

    private HashMap<Marker, Shop> markers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readData();

        search = findViewById(R.id.main_search);
        searchButton = findViewById(R.id.main_searchImg);
        layout = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        menuOpen = findViewById(R.id.main_menuButton);
        menuOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.openDrawer(GravityCompat.START);
            }
        });
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                //37.3943° N, 126.9568° E
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.3943, 126.9568), 15));
                filter("음식점", R.drawable.icon_restaurant);

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Shop shop = markers.get(marker);
                        if(shop != null) {
                            Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
                            intent.putExtra("shop", shop);
                            startActivity(intent);
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shop result = search(search.getText().toString());

                if(result != null) {
                    moveCamera(result);
                } else {
                    Toast.makeText(getApplicationContext(), "해당하는 이름의 매장이 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.restaurant).setOnClickListener((e) -> filter("음식점", R.drawable.icon_restaurant));
        findViewById(R.id.cafe).setOnClickListener((e) -> filter("카페", R.drawable.icon_cafe));
        findViewById(R.id.hospital).setOnClickListener((e) -> filter("병원", R.drawable.icon_hospital));
        findViewById(R.id.madicline).setOnClickListener((e) -> filter("약국", R.drawable.icon_madic));
        findViewById(R.id.bread).setOnClickListener((e) -> filter("제빵", R.drawable.icon_bread));
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Toast.makeText(getApplicationContext(), "Touched", Toast.LENGTH_SHORT).show();
//                switch (menuItem.getItemId()) {
//                    case R.id.restaurant:
//                        filter("음식점");
//                        break;
//                    case R.id.cafe:
//                        filter("카페");
//                        break;
//                    case R.id.hospital:
//                        filter("병원");
//                        break;
//                    case R.id.madicline:
//                        filter("약국");
//                        break;
//                    case R.id.bread:
//                        filter("제빵");
//                        break;
//                    default:
//                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//                layout.closeDrawer(GravityCompat.START);
//
//                return true;
//            }
//        });
    }

    public Shop search(String keyword) {

        for(Shop shop : filtered) {
            if(shop.shopName.equals(keyword)) {
                return shop;
            }
        }

        return null;
    }

    public void moveCamera(Shop shop) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(shop.latitude, shop.longitude), 16));
    }

    public void filter(String type, int resource) {
        if(map != null) {
            layout.closeDrawer(GravityCompat.START);
            filtered.clear();
            map.clear();
            markers.clear();

            for (Shop shop : shops) {
                Log.d("shops", shop.shopType);

                if (shop.shopType.contains(type)) {
                    filtered.add(shop);
                    MarkerOptions options = new MarkerOptions();
                    options.position(new LatLng(shop.latitude, shop.longitude));
                    options.title(shop.shopName);
                    options.snippet(shop.address);

                    Bitmap bitmap = Bitmap.createScaledBitmap(((BitmapDrawable)getResources().getDrawable(resource)).getBitmap(), 75, 75, false);

                    options.icon(BitmapDescriptorFactory.fromBitmap(bitmap));

                    markers.put(map.addMarker(options), shop);
                }
            }

            Toast.makeText(getApplicationContext(), "필터링: " + filtered.size(), Toast.LENGTH_LONG).show();
        }
    }



    public void readData() {
        CsvReader csvReader = new CsvReader();

        try {
            csvReader.setContainsHeader(true);

            CsvContainer container = csvReader.read(new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.data))));
            for(CsvRow row : container.getRows()) {
                Shop shop = new Shop();
                shop.shopName = row.getField(1);
                shop.shopCode = row.getField(2);
                shop.shopCondition = row.getField(3);
                shop.shopType = row.getField(4);
                shop.address = row.getField(5);
                shop.addressOld = row.getField(6);
                shop.tel = row.getField(7);
                shop.availableLocalCurrency = row.getField(8);
                shop.currencyRealistic = row.getField(9);
                shop.currencyVirtual = row.getField(10);
                shop.currencyMobile = row.getField(11);
                shop.postalCode = row.getField(12);
                shop.latitude = Double.parseDouble(row.getField(13));
                shop.longitude = Double.parseDouble(row.getField(14));
                shop.date = row.getField(15);

                shops.add(shop);
            }

            Log.d("spotviewer", "shops: " + shops.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
