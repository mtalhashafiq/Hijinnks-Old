package com.example.mtalh.hijinnks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


@SuppressLint("ValidFragment")
public class Edit_ProfileFragment extends AppCompatDialogFragment {


    HomeFragmentInterface homeFragmentInterface;
    ImageView back_button, cover_photo_main;
    CircleImageView cover_photo, profile_change_button, profile_image;
    boolean profile_photo_check = true;
    boolean cover_photo_check = true;
    boolean profile_photo_check_shared = false;
    boolean cover_photo_check_shared = false;
    Drawable profile_drawable;
    Button save_edit_profile;
    EditText email_edittext;
    TextView more_option_edit_profile;
    CropImage.ActivityResult result_profile;
    CropImage.ActivityResult result_cover;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private OnFragmentInteractionListener mListener;

    /*public Edit_ProfileFragment(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }*/
    public Edit_ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit__profile, container, false);
        final FragmentManager fm = getFragmentManager();
        back_button = (ImageView) view.findViewById(R.id.back_image);
        cover_photo_main = (ImageView) view.findViewById(R.id.cover_photo_main);
        cover_photo = (CircleImageView) view.findViewById(R.id.cover_photo_edit_profile);
        profile_change_button = (CircleImageView) view.findViewById(R.id.profile_change_buutton);
        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        save_edit_profile = (Button) view.findViewById(R.id.save_edit_profile);
        final HomeFragment homeFragment = new HomeFragment(homeFragmentInterface);
        email_edittext=(EditText)view.findViewById(R.id.email_edittext);
        more_option_edit_profile=(TextView)view.findViewById(R.id.more_option_edit_profile);
        final Setting_Fragment setting_fragment=new Setting_Fragment(homeFragmentInterface);
        //captureImageInitialization();
//   getActivity().getContext().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#41539b"));
        }

        final Uri imageuri = null;
        if (!get_profile_picture().equals("get_profile_sharedprefrences")) {
//            getActivity().getWindow().setStatusBarColor(Color.GRAY);


            Uri get_profile_sharedprefrences = Uri.parse(get_profile_picture());

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(get_profile_sharedprefrences);
                profile_drawable.createFromStream(inputStream, get_profile_sharedprefrences.toString());
            } catch (FileNotFoundException e) {
                profile_drawable = getResources().getDrawable(R.drawable.hijinnks_logo);

            }
            ((ImageView) view.findViewById(R.id.profile_image)).setImageURI(get_profile_sharedprefrences);
        }

        email_edittext.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_mail_icon, 0, R.drawable.ic_verify_email_icon, 0);
        if (!get_cover_picture().equals("get_cover_sharedprefrences")) {
            Uri abc = Uri.fromFile(new File(get_cover_picture()));
            Drawable yourDrawable;
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(abc);
                yourDrawable = Drawable.createFromStream(inputStream, abc.toString());
            } catch (FileNotFoundException e) {
                yourDrawable = getResources().getDrawable(R.drawable.hijinnks_logo);
            }
            ((ImageView) view.findViewById(R.id.cover_photo_main)).setBackground(yourDrawable);
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                homeFragmentInterface.icon_change_method_on_back_click(3);
             /*   android.support.v4.app.FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.home_framelayout, setting_fragment);
                fragmentTransaction.commit();*/

            }
        });
        profile_change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .setActivityTitle("My Crop")
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setCropMenuCropButtonTitle("Done")
                        .setRequestedSize(8000, 80)
                        .setCropMenuCropButtonIcon(R.mipmap.ic_launcher);
                // .start((Activity) getContext());

                Intent intent = CropImage.activity(imageuri).getIntent(getContext());
                startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
                profile_photo_check = true;
                cover_photo_check = false;
                profile_photo_check_shared = true;
            }
        });

        cover_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .setActivityTitle("My Crop")
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setCropMenuCropButtonTitle("Done")
                        .setRequestedSize(8000, 80)
                        .setCropMenuCropButtonIcon(R.mipmap.ic_launcher);
                // .start((Activity) getContext());

                Intent intent = CropImage.activity(imageuri).getIntent(getContext());
                startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
                cover_photo_check = true;
                profile_photo_check = false;
                cover_photo_check_shared = true;

            }
        });
        save_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (profile_photo_check_shared == true) {
                    String profile_sharedprefrences_string;
                    profile_sharedprefrences_string = (result_profile.getUri()).toString();
                    store_profile_picture(profile_sharedprefrences_string);
                }
                if (cover_photo_check_shared == true) {
                    String stringUri;
                    stringUri = (result_cover.getUri()).getPath();
                    store_cover_picture(stringUri);
                }
            }
        });
        more_option_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, new Setting_Fragment(homeFragmentInterface));
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            result_profile = CropImage.getActivityResult(data);
            result_cover = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                assert extras != null;
                Bitmap photo = extras.getParcelable("result");
                Drawable drawable = new BitmapDrawable(getResources(), photo);
//                ((ImageView) getActivity().findViewById(R.id.cover_photo_main)).setBackground(drawable);
                if (profile_photo_check == true && cover_photo_check == false) {
                    ((ImageView) getActivity().findViewById(R.id.profile_image)).setImageURI(result_profile.getUri());
                    //((ImageView) getActivity().findViewById(R.id.profile_image)).setBackground(((ImageView) getActivity().findViewById(R.id.profile_image)).getDrawable());
                    // ((ImageView) getActivity().findViewById(R.id.profile_image)).setImageURI(null);

                   /* String profile_sharedprefrences_string;
                    profile_sharedprefrences_string = (result.getUri()).toString();
                    store_profile_picture(profile_sharedprefrences_string);*/

                }
                if (cover_photo_check == true && profile_photo_check == false) {
                    ((ImageView) getActivity().findViewById(R.id.cover_photo_main)).setImageURI(result_cover.getUri());
                    ((ImageView) getActivity().findViewById(R.id.cover_photo_main)).setBackground(((ImageView) getActivity().findViewById(R.id.cover_photo_main)).getDrawable());
                    ((ImageView) getActivity().findViewById(R.id.cover_photo_main)).setImageURI(null);

                   /* String stringUri;
                    stringUri = (result.getUri()).getPath();
                    store_cover_picture(stringUri);*/

                }
                Toast.makeText(getContext(), "Cropping successful, Sample: " + result_profile.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(getContext(), "Cropping failed: " + result_profile.getError(), Toast.LENGTH_LONG).show();
            }

        }
    }

    private void store_profile_picture(String profile_sharedprefrences) {
        SharedPreferences store_profile_picture = this.getActivity().getSharedPreferences("Profile Shared Prefrences", MODE_PRIVATE);
        SharedPreferences.Editor editor = store_profile_picture.edit();
        editor.putString("profie_shared_prefrences", profile_sharedprefrences);
        editor.apply();
    }

    private String get_profile_picture() {
        SharedPreferences get_profile_picture = this.getActivity().getSharedPreferences("Profile Shared Prefrences", MODE_PRIVATE);
        return get_profile_picture.getString("profie_shared_prefrences", "get_profile_sharedprefrences");
    }


    private void store_cover_picture(String profile_sharedprefrences) {
        SharedPreferences store_cover_picture = this.getActivity().getSharedPreferences("Cover Shared Prefrences", MODE_PRIVATE);
        SharedPreferences.Editor editor = store_cover_picture.edit();
        editor.putString("cover_shared_prefrences", profile_sharedprefrences);
        editor.apply();
    }


    private String get_cover_picture() {
        SharedPreferences get_cover_picture = this.getActivity().getSharedPreferences("Cover Shared Prefrences", MODE_PRIVATE);
        return get_cover_picture.getString("cover_shared_prefrences", "get_cover_sharedprefrences");
    }


    /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
