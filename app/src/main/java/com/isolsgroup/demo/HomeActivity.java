package com.isolsgroup.demo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.stetho.Stetho;
import com.google.android.material.navigation.NavigationView;
import com.isolsgroup.demo.adapter.HomeAdapter;
import com.isolsgroup.demo.listener.OnHomeItemClickListener;
import com.isolsgroup.demo.model.HomeResponse;

import java.util.ArrayList;
import java.util.List;

import static com.isolsgroup.demo.R.menu.my_menu;

public class HomeActivity extends AppCompatActivity implements OnHomeItemClickListener {

    private List<HomeResponse> mList = new ArrayList<> ();
    private String[] productID = new String[]{"1", "2","3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
    private String[] productName = new String[]{"Brightening Serum", "Beautyblender Re-Dew", "Clinique iD", "Joico Defy Damage Protective Shield", "Radiance-Perfecting Foundation",
                                               "Beauty PlantGenius Survival Serum", "Algenist Genius Sleeping Collagen", "Sunday Riley A+ Serum", " Amplifying Night Mask",
                                                "Vintner's Daughter Active", "Finish Liquid Foundation", "A-Passioni Retinol Cream", " Beauty Power Fabric Foundation ",
                                                "High Cover Radiant Concealer", "Ultra HD Self-Setting Concealer", "Multi-Use Glow Stick", "New York Lips", "FetishEyes Mascara",
                                                "Maybelline New York Snapscara", "Benefit Roller Liner", "Eye Shadow Palettes", "Bobbi Brown Crushed Liquid Lip" ,
                                                "Yves Saint Laurent Beauté", "Dior Lip Glow To The Max", "Living Proof Restore Dry"};
    private String[] productPrice = new String[]{"Rs 700", "Rs 2000","Rs 850", "Rs 950", "Rs 699", "Rs 900", "Rs 499", "Rs 1999", "Rs 900", "Rs 650", "Rs 900", "Rs 600",
            "Rs 2000", "Rs 499", "Rs 899", "Rs 999", "Rs 350", "Rs 789", "Rs 870", "Rs 299", "Rs 780", "Rs 890", "Rs 1900", "Rs 6999", "Rs 6990"};
    private String[] productDescription = new String[]{"Vitamin C may not be the new kid on the skin-care ingredient block, but it remains one of the most popular year after year. Building on the success of their Violet-C Radiance Mask, this new oil- and fragrance-free serum packs a whopping 20 percent concentration of vitamin C (and two different types at that) to deliver both instant and long-term results.",
            "From the brand that brought you one of our favorite makeup applicators of all time (it's won a Best of Beauty Award countless years in a row) comes this face mist, which promises to render makeup touch-ups obsolete.",
            "Personalized beauty is one trend that isn't going anywhere in 2019. Case in point: Clinique iD a customized hydration system based around the signature Dramatically Different Moisturizer lotions, which is set to be the brand's biggest launch since 2010's Even Better Clinical line. ",
            "Unlike any other hair protectant we've seen, this touts a unique delivery system that continuously releases a trio of protective and strengthening ingredients--keratin, rose hip oil, and arginine--to shield your strands from both heat styling, UV damage, and pollution.",
            "A pearl extract in this medium-to-full coverage base helps reflect light to give your skin an instant glow, regardless of whether you're standing in front of a ring light or sitting under yellow-y office bulbs.",
            "This all-natural multitasker is the true definition of a skin-care-makeup hybrid. The formula combines brightening vitamin C and licorice extract with a trio of acids (glycolic, lactic, and azaleic) to knock out dark spots and dullness, while squalene and hyaluronic acid pack a hit of hydration.",
            "Those looking to up their retinoid game will appreciate that this serum contains an impressive 6.5 percent blend of retinoids and retinoid-like botanicals for smoother, clearer skin.",
            "The OG botanical serum from this brand has hoards of devoted fans (including Allure editors), all of whom we are willing to bet will go crazy for this new launch. At work: a complex of plant-based ingredients sourced ",
            "From 12 of the world's most nutritionally dense plants available, plus stabilized vitamin C, two kinds of hyaluronic acid, probiotics and prebiotics, and lactic acid.",
            "We highly doubted anything could rival the Hourglass Vanish Seamless Finish Foundation Stick...but then this newbie came around. Not only does it somehow strike the unicorn-like balance of being totally light in texture while still being a full coverage formula, it also features unique coated pigments that deliver said full coverage with just a half pump of product. ",
            "One percent (vegan) retinol plus peptides make for the ultimate wrinkle-fighting dream team while evening out tone and texture, too. The addition of vitamin F plus apricot and jojoba oils adds moisture and counteracts any drying effects, while a long list of superfoods deliver antioxidant protection.",
            "A blend of waxes and oils gives this foundation formula its initial balm-like consistency, which, when smoothed over skin, transforms into a cream, and later, a powder. Made with 15 percent pigments, it packs a hefty punch of complete coverage and has a perfectly matte finish despite its creamy consistency.",
            "We'd never knock the O.G. Touche Éclat, but there's no denying that while it's sheer consistency makes it ideal for brightening skin, it's definitely not enough to conceal what needs concealing (dark circles, we're looking at you).",
            "Having to set and touch up your concealer is so 2018. The latest addition to the brand's best-selling Ultra HD line (replacing the original Ultra HD Concealer), this cover-up doesn't need to be set with powder, thanks to the unique amino-acid coated pigments that immediately conform to your skin, staying put for up to 12 hours without caking or creasing. ",
            "We first saw this multi-purpose balm on the Chanel spring 2019 runway and have been counting the days until it launches. Created by the brand's resident makeup artist Lucia Pica, the Transparent shade delivers a sheer glow (so it's perfect for trying out the glass-skin trend), while the Sculpting shade lays down a dewy, pearlescent shimmer. ",
            "After nearly two decades of launching covetable fragrance collections, Bond No.9 makes their first foray into cosmetics with a line of nine different red lipsticks, each named after a different area of New York City. ",
            "This newbie made its debut on the runway during Milan Fashion Week, where it was used to create the 1960s-inspired, doll-like lashes at the Prada show.",
            "Maybelline knows a thing or two about mascara, and their newest Snapscara formula is one of their best yet. The thin, flexible pigment tints your lashes and leaves them looking super glossy, but also adds a little thickness and lots of length.",
            "For super straight, precise lines, this newbie can't be beat. Available in matte black and matte brown, the extra-fine felt tip of this pen glides on smoothly without dragging, making it easy for even the clumsiest makeup lover to draw on clean wings. ",
            "Each of the two palettes features a dozen shades (one with warmer hues, one with cooler) in a mix of matte, shimmer, and sparkle shadows. We love 'em all, but it's the sparkly ones that really caught our eye (get it?). ",
            "A spin-off of the brand's Crushed Lip Color, this liquid version took the same idea but tweaked it for someone who prefers a bolder lip look. It has the creamy moisture of a balm (like the original), the full pigment of a liquid lip color, and the sheen of a gloss. ",
            "The same color and shine pay-off as the brand's Rouge Volupté Shine, this formula promises to plump your lips as well. The little black heart in the core isn't just a cute design decision, it contains peppermint oil and caprilyl glycol to make lips look fuller, without any painful stinging or burning.",
            "Dior Lip Glow To The Max is the same beloved Lip Glow, now with a yummy new look and a touch more pigment. In this new formula, the sheer, tinted balm is spiked with pigment. ",
            "Those smarty-pants chemists and hairstylists at Living Proof have done it again, this time, with a leave-in treatment that both hydrates and soothes a dry, itchy scalp.",
            "Unlike any other hair protectant we've seen, this touts a unique delivery system that continuously releases a trio of protective and strengthening ingredients--keratin, rose hip oil, and arginine--to shield your strands from both heat styling, UV damage, and pollution",
    };

  //  private int[] productImages = new int[]{R.drawable.logo, R.drawable.logo,R.drawable.logo, R.drawable.logo};

    private String[] productImages = new String[]{"https://media.allure.com/photos/5c1933c41f198f2c6627f8c8/1:1/w_1600%2Cc_limit/tatcha-violet-c-serum.jpg",
          "https://media.allure.com/photos/5bfd4527a921cb2f21747a23/1:1/w_1600%2Cc_limit/ReDew.png",
          "https://media.allure.com/photos/5bfc01ced5590e42469e61eb/1:1/w_1600%2Cc_limit/CliniqueID.png",
          "https://media.allure.com/photos/5c643030afca3e2c7cfc8048/1:1/w_1600%2Cc_limit/Joico.png",
          "https://media.allure.com/photos/5bfc01cfc1aed93f02ec47a5/1:1/w_1600%2Cc_limit/LauraMercier.png",
          "https://media.allure.com/photos/5c12aeb2c13b245d385e8c51/1:1/w_1600%2Cc_limit/alpyn%2520beauty.jpg",
          "https://media.allure.com/photos/5bfc35a6e2f5932d13493a64/1:1/w_1600%2Cc_limit/Sleeping%2520Collagen%2520Lid%2520on%2520Straight%2520On.png",
          "https://media.allure.com/photos/5c40df3829de594cea80eb99/1:1/w_1600%2Cc_limit/SundayRiley.png",
          "https://media.allure.com/photos/5bfc01cc2333702d3fb1a4ab/1:1/w_1600%2Cc_limit/15326-UTM-S-Amplifying_Night_Mask-Shade-1807-Product-3000-1.jpg",
          "https://media.allure.com/photos/5c643031f3305f2c7343851a/1:1/w_1600%2Cc_limit/VD.png",
          "https://media.allure.com/photos/5c4b44692be8686e611a3532/1:1/w_1600%2Cc_limit/hourglass.png",
          "https://media.allure.com/photos/5bfd4271d5590e42469e6369/1:1/w_1600%2Cc_limit/Retinol_30ml_2000x2000.jpg",
          "https://media.allure.com/photos/5bfc3f5ec1aed93f02ec47ae/1:1/w_1600%2Cc_limit/Armani.png",
          "https://media.allure.com/photos/5c41305cb44f422c9f00c70d/1:1/w_1600%2Cc_limit/concealer.jpg",
          "https://media.allure.com/photos/5bfc01cea921cb2f21747a09/1:1/w_1600%2Cc_limit/MUFEUHDConcealer.jpg",
          "https://media.allure.com/photos/5c12abde177eb32c66a6e57c/1:1/w_1600%2Cc_limit/CHANEL%2520BEAUTY%2520SCULPTING%2520AND%2520TRANSPARENT%2520STICKS.jpg",
          "https://media.allure.com/photos/5c4b86232cab492d0efe8420/1:1/w_1600%2Cc_limit/BondLip.png",
          "https://media.allure.com/photos/5c34dd7d9b13b32ced00e913/1:1/w_1600%2Cc_limit/Pat-McGrath-Labs-Fetish-Eyes-Mascara.jpg",
          "https://media.allure.com/photos/5c13e17f4e9df77981b56a97/1:1/w_1600%2Cc_limit/Gallery%2520MNY%2520Snacpscara.jpg",
          "https://media.allure.com/photos/5c17b3ff1f198f2c6627f8b0/1:1/w_1600%2Cc_limit/Roller%2520Liner%2520black_open.png",
          "https://media.allure.com/photos/5c6eeb9b21faeb781c8df6bf/1:1/w_1600%2Cc_limit/Stila-After-Hours-Happy-Hour-Palettes.jpg",
          "https://media.allure.com/photos/5c0802bb4691a52cf2eb0829/1:1/w_1600%2Cc_limit/BobbiBrownLip.png",
          "https://media.allure.com/photos/5c13e8a48d756a78dacc3e61/1:1/w_1600%2Cc_limit/YSL.png",
          "https://media.allure.com/photos/5c17b64e1f198f2c6627f8b2/1:1/w_1600%2Cc_limit/DiorLipGlow.png",
          "https://media.allure.com/photos/5c12c8ccd6108d72cf5072a5/1:1/w_1600%2Cc_limit/LivingProofscalp.png"};

    RecyclerView recyclerView;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageView closedImageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
         setContentView (R.layout.activity_home);

        Stetho.initializeWithDefaults(this);
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle((CharSequence) "Preeti");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable (Color.argb(0, 244, 67, 54)));
        this.drawer = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        this.closedImageButton = this.navigationView.getHeaderView(0).findViewById(R.id.image_button);
        this.navigationView.setItemIconTintList(null);
        //    TextView txtName = (TextView) this.navigationView.getHeaderView(0).findViewById(R.id.name);
        //    txtName.setText(strUserName);
        this.closedImageButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
            }
        });
        setUpNavigationView();

        mList = new ArrayList<>();
        for (int i = 0; i < productImages.length; i++) {
            HomeResponse homeResponse = new HomeResponse();
            homeResponse.setProductName(productName[i]);
            homeResponse.setProductPrice(productPrice[i]);
            homeResponse.setProductDescription(productDescription[i]);
            homeResponse.setProductID (productID[i]);
            homeResponse.setImages(productImages[i]);
            mList.add(homeResponse);
        }
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager (getApplicationContext(), 1));
        HomeAdapter homeAdapter = new HomeAdapter(mList, Glide.with(getApplicationContext()));
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setClickListener(HomeActivity.this);
    }

    private void setUpNavigationView() {
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = null;
                switch (menuItem.getItemId()) {
                    case R.id.about_icon:
                        intent = new Intent(HomeActivity.this, AboutUsActivity.class);
                //        intent.putExtra("strUserLoggedIn", strUserLoggedIn);
                        startActivity(intent);
                        drawer.closeDrawers();
                        break;
                    case R.id.cart_icon:
                        intent = new Intent(HomeActivity.this, DishCartActivity.class);
                //        intent.putExtra("strUserLoggedIn", strUserLoggedIn);
                        startActivity(intent);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_share:
                        Intent sharingIntent = new Intent("android.intent.action.SEND");
                        sharingIntent.setType("text/plain");
                        String shareBody = "http://play.google.com/store/apps/details?id=" + HomeActivity.this.getApplicationContext().getPackageName();
                        sharingIntent.putExtra("android.intent.extra.SUBJECT", "Check Out : JMD Automobile App");
                        sharingIntent.putExtra("android.intent.extra.TEXT", shareBody);
                        HomeActivity.this.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        drawer.closeDrawers();
                        break;

                    case R.id.logout_icon:
                        logoutMethod();
                        break;

                    default:
                        break;
                }
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                return true;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawer, this.toolbar, R.string.openDrawer, R.string.closeDrawer) {
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        this.drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void logoutMethod(){
        SessionManager sessionManager = new SessionManager (HomeActivity.this);
  //      SQLiteHandler sqLiteHandler = new SQLiteHandler (HomeActivity.this);
        sessionManager.setLogin (false);
  //      sqLiteHandler.deleteUser ();
        startActivity (new Intent (HomeActivity.this, LoginActivity.class));
        finish ();
    }


    @Override
    public void OnHomeAdapterItemClicked(int position) {
        Intent intent = new Intent (HomeActivity.this, ProductDetailActivity.class);
        intent.putExtra ("productImage", mList.get (position).getImages ());
        intent.putExtra ("productName", mList.get (position).getProductName ());
        intent.putExtra ("productPrice", mList.get (position).getProductPrice ());
        intent.putExtra ("productDetails", mList.get (position).getProductDescription ());
        intent.putExtra ("productID", mList.get (position).getProductID ());
        startActivity (intent);
    }
}
