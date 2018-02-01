package com.example.mtalh.hijinnks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
//import static com.example.mtalh.hijinnks.ProfileFragment.list_profile_Invites_Rsvpd;

/**
 * Created by mtalh on 26-Oct-17.
 */

public class RecyclerAdapter_Home_User_Profile_Invite_Rsvpd extends RecyclerView.Adapter<RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.RecyclerViewHolder> {
    static RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.ItemClickListener itemClickListener;
    ArrayList<Model_User_Profile_Invites_rsvpd> list_profile_invites_rsvpds = new ArrayList<>();
    String[] name;
    Context context;
    boolean video_pause_play = false;

    public RecyclerAdapter_Home_User_Profile_Invite_Rsvpd(ArrayList<Model_User_Profile_Invites_rsvpd> list_profile_invites_rsvpds, Context context) {
        this.list_profile_invites_rsvpds = list_profile_invites_rsvpds;
        this.context = context;
    }

    @Override
    public RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_invites_profile__fragment, parent, false);
        RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.RecyclerViewHolder recyclerviewholder = new RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.RecyclerViewHolder(view);

       /* RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight();
        view.setLayoutParams(lp);*/
        return recyclerviewholder;

    }


    @Override
    public void onBindViewHolder(final RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.RecyclerViewHolder holder, int position) {

        holder.Name.setText(list_profile_invites_rsvpds.get(position).getName());
        holder.profile_type.setText(list_profile_invites_rsvpds.get(position).getType());

       // holder.videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.a));
        // holder.videoView.setMediaController(new MediaController(context));
        holder.videoView.requestFocus();

        holder.video_background_play.setOnClickListener(new View.OnClickListener() {
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
        });
        holder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                holder.video_background_play.setVisibility(View.VISIBLE);
                holder.video_play_pause.setVisibility(View.VISIBLE);

                holder.video_background_play
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Toast.makeText(context, "Click Performd", Toast.LENGTH_SHORT).show();
                                if (video_pause_play == true) {
                                    holder.videoView.pause();
                                    holder.video_play_pause.setImageResource(R.drawable.pause);
                                    video_pause_play = false;
                                }
                            }
                        });


                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list_profile_invites_rsvpds.size();
    }

    public void SsetClickListener(RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name, profile_type;
        VideoView videoView;
        ImageView video_background_play, video_play_pause;


        public RecyclerViewHolder(View view) {
            super(view);
            profile_type = (TextView) view.findViewById(R.id.profile_type);
            Name = (TextView) view.findViewById(R.id.video_name);
            videoView = (VideoView) view.findViewById(R.id.video_view);
            video_background_play = (ImageView) view.findViewById(R.id.video_background_play);
            video_play_pause = (ImageView) view.findViewById(R.id.video_play);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }
}