package com.elementalbraines.expressapp.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elementalbraines.expressapp.R;
import com.elementalbraines.expressapp.Util;
import com.elementalbraines.expressapp.models.Chat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tumblr.remember.Remember;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Maurel on 25/11/2017.
 */

public class ChatAdapter  extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    List<Chat> chatList;
    Context context;
    private static final int CHAT_SEND = 60;
    private static final int CHAT_RECEIVED = 61;


    public ChatAdapter(Context context, List<Chat> chats){
        this.context = context;
        this.chatList = chats;

    }

    public void addChat(Chat chat){
        this.chatList.add(chat);
        notifyDataSetChanged();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == CHAT_SEND) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_send, parent, false);
            return new ChatViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ChatViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(chatList.get(position).getUser_id().equals(Remember.getString(Util.USER_ID,"")))
            return CHAT_SEND;
        else
            return CHAT_RECEIVED;

    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        Uri uri = Uri.parse(chat.getImage());
        holder.txvChat.setText(chat.getMensaje());
        holder.txvChatName.setText(chat.getNombre());
        if(holder.sdvChatImg != null)
            holder.sdvChatImg.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txvChatName)
        TextView txvChatName;
        @BindView(R.id.txvChat)
        TextView txvChat;

        SimpleDraweeView sdvChatImg;

        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            sdvChatImg= (SimpleDraweeView)itemView.findViewById(R.id.sdvChatImg);

        }
    }
}