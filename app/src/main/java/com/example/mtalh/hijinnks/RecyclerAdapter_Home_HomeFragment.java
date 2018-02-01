package com.example.mtalh.hijinnks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by mtalh on 24-Oct-17.
 */

public class RecyclerAdapter_Home_HomeFragment extends RecyclerView.Adapter<RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder> {

    static ItemClickListener itemClickListener;
    String[] name;
    String[] video;
    Context context;
    boolean video_pause_play = false;
    FragmentTransaction fragmentTransaction;
    private Map_Fragment mapFragment = new Map_Fragment();
    private User_Profile_Fragment user_profile_fragment = new User_Profile_Fragment();

    //String videourl="http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    public RecyclerAdapter_Home_HomeFragment(String[] name, String[] video, Context context) {
        this.name = name;
        this.video = video;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  if (parent == null) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_home_homefragment, parent, false);
        RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder recyclerviewholder = new RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder(view);
        /*RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight();
        view.setLayoutParams(lp);*/
        return recyclerviewholder;
        //  } else {
//            RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder recyclerviewholder = new RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder(parent);
//            return recyclerviewholder;
        //  }

    }


    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        holder.Name.setText(name[position]);

        // holder.videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + video[position]));
        // holder.videoView.setMediaController(new MediaController(context));
        // holder.videoView.requestFocus();
//        if (holder.isRecyclable()) {
//            new AsyncVideoPlay(holder.videoView).execute();
//        } else {
        new AsyncVideoPlay(holder.videoView, holder.progess_bar_video_main_class).execute();

//        }

      /*  Bitmap thumb = ThumbnailUtils.createVideoThumbnail("android.resource://" + context.getPackageName() + "/" + R.raw.c, MediaStore.Images.Thumbnails.MINI_KIND);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);
        videoView.setBackgroundDrawable(bitmapDrawable);*/

      /*  holder.video_background_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (video_pause_play == false) {
                    holder.videoView.start();
                    holder.video_play_pause.setImageResource(R.drawable.video_play_play);
                    video_pause_play = true;
                    holder.video_background_play.setVisibility(View.INVISIBLE);
                    holder.video_play_pause.setVisibility(View.INVISIBLE);
                }
            }
        });*/
   /*     holder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
*/


        holder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                new AsyncVideoPlay(holder.videoView).execute();
                // new AsyncVideoPlay(holder.videoView, holder.progess_bar_video_main_class).execute();

                /*MediaController mediacontroller = new MediaController(context);
                mediacontroller.setAnchorView(holder.videoView);
                holder.videoView.setMediaController(mediacontroller);*/
                if (video_pause_play == false) {

                    holder.videoView.start();
                    holder.video_play_pause.setImageResource(R.drawable.video_play_play);
                    video_pause_play = true;
                    holder.video_background_play.setVisibility(View.INVISIBLE);
                    holder.video_play_pause.setVisibility(View.INVISIBLE);
                } else {
                    holder.videoView.pause();
                    holder.video_play_pause.setImageResource(R.drawable.video_play_play);
                    holder.video_background_play.setVisibility(View.VISIBLE);
                    holder.video_play_pause.setVisibility(View.VISIBLE);
                    video_pause_play = false;
                }
                return false;
            }
        });
        holder.open_map_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                Fragment myFragment = new TaskApprovalFragmentDetails();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_framelayout, mapFragment).addToBackStack(null).commit();

             /*   fragmentTransaction = Activity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout,mapFragment);
                fragmentTransaction.commitAllowingStateLoss();*/
            }
        });
        holder.user_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_framelayout, user_profile_fragment).addToBackStack(null).commit();

            }
        });
        holder.comment_section_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent comment_activity = new Intent(activity, Comments.class);
                activity.startActivity(comment_activity);
            }
        });
   /*
        holder.video_background_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "button click", Toast.LENGTH_SHORT).show();
                if (video_pause_play == true) {
                    video_pause_play = false;
                    holder.videoView.pause();
                    holder.video_play_pause.setImageResource(R.drawable.pause);
                } else {
                    holder.videoView.start();
                    holder.video_play_pause.setImageResource(R.drawable.video_play_play);
                    holder.video_background_play.setVisibility(View.INVISIBLE);
                    holder.video_play_pause.setVisibility(View.INVISIBLE);
                    video_pause_play = true;
                }
                holder.videoView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        Toast.makeText(context, "video click", Toast.LENGTH_SHORT).show();
                        holder.video_background_play.setVisibility(View.VISIBLE);
                        holder.video_play_pause.setVisibility(View.VISIBLE);
                        if (video_pause_play == true) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    holder.video_background_play.setVisibility(View.INVISIBLE);
                                    holder.video_play_pause.setVisibility(View.INVISIBLE);
                                }
                            }, 2000);
                        }
                      *//*  else {
                            holder.video_background_play.setVisibility(View.VISIBLE);
                            holder.video_play_pause.setVisibility(View.VISIBLE);
                        }*//*
                        return false;
                    }
                });
            }
        });*/


        /*        return false;
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return name.length;
    }

    public void SsetClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name;
        VideoView videoView;
        ImageView video_background_play, video_play_pause;
        LinearLayout open_map_fragment;
        ProgressBar progess_bar_video_main_class;
        CircleImageView user_profile_image;
        LinearLayout comment_section_screen;

        public RecyclerViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.profile_name);
            videoView = (VideoView) view.findViewById(R.id.videoview);
            video_background_play = (ImageView) view.findViewById(R.id.video_background_play);
            video_play_pause = (ImageView) view.findViewById(R.id.video_play);
            progess_bar_video_main_class = (ProgressBar) view.findViewById(R.id.progess_bar_video);
            open_map_fragment = (LinearLayout) view.findViewById(R.id.open_map_fragment);
            comment_section_screen = (LinearLayout) view.findViewById(R.id.comment_section_screen);
            user_profile_image = (CircleImageView) view.findViewById(R.id.user_profile_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }

    public class AsyncVideoPlay extends AsyncTask<String, String, String> {

        private static final String TEST_URL_2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        VideoView videoView2;
        MediaController mediaController;
        ProgressBar progess_bar_video;
        private String TEST_URL_3 = "http://www.demonuts.com/Demonuts/smallvideo.mp4";
        private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                Log.e("ERRORMEDIAPLAYER", "" + mp);
                return true;
            }
        };

        public AsyncVideoPlay(VideoView videoView, ProgressBar progess_bar_video) {
            this.videoView2 = videoView;
            this.progess_bar_video = progess_bar_video;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progess_bar_video.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
              /*  int i;
                int videoarray[]={R.raw.a,R.raw.b,R.raw.c};
                int video_length[]=new int[videoarray.length];
                for (i=0;i<video.length;i++);
                {
                    videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" +video_length[i]));
                }*/


                //  videoView2.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.b));

                //  TEST_URL_3= java.net.URLDecoder.decode(TEST_URL_3, "UTF-8");
              /*  Uri video = Uri.parse(TEST_URL_2);
                videoView2.setVideoURI(video);
                videoView2.setOnErrorListener(mOnErrorListener);*/
                try {
                    // Start the MediaController

                    // Get the URL from String VideoURL
                    Uri video2 = Uri.parse(TEST_URL_2);
                    videoView2.setVideoURI(video2);


                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

                videoView2.requestFocus();

                videoView2.seekTo(3000);
                Bitmap thumb = ThumbnailUtils.createVideoThumbnail(TEST_URL_3, MediaStore.Images.Thumbnails.MINI_KIND);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);
                videoView2.setBackgroundDrawable(bitmapDrawable);


              /*  video_thumbnail.setVideoURI(Uri.parse(uri));
                video_thumbnail.seekTo(10000);*/
              /*  Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(uri, MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(thumbnail);
                videoView.setBackgroundDrawable(bitmapDrawable);
*/

                // String uri = "android.resource://" + context.getPackageName() + "/" + R.raw.b;
                //  videoView2.setCallback((EasyVideoCallback) context.getApplicationContext());
              /*  String path = Environment.getExternalStorageDirectory().getPath();
                path = path + "/q.mp4";
                Uri mUri = Uri.fromFile(new File(path));
//                videoView2.setSource(mUri);
                videoView2.setSource(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.a));
                videoView2.setSource(Uri.parse(TEST_URL));
                videoView2.start();
                videoView2.hideControls();*/
                //videoView2.setSource(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.c));
                //   videoView2.setSource(Uri.fromFile(new File(String.valueOf(R.raw.c))));


//                Uri videoFile = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.b);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progess_bar_video.setVisibility(View.GONE);
            // videoView2.start();
        }

    }

}