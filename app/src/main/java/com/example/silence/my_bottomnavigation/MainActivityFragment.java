package com.example.silence.my_bottomnavigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Silence on 2018/3/14.
 */

public class MainActivityFragment extends Fragment {
    RecyclerView mRecyclerView;
    private ToolbarScrollHelper scrollHelper;
    ViewGroup mRoot;
    public MainActivityFragment() { }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView01);
    }// @Nullable  意味着可以传入null值

    static class TwoLinesViewHolder extends RecyclerView.ViewHolder {// 列表项的类
        final TextView title;
        final TextView description;
        final ImageView imageView;
        final Button button1;
        final Button button2;
        final int marginBottom;

        public TwoLinesViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(android.R.id.title);
            description = (TextView) itemView.findViewById(android.R.id.text1);
            imageView = (ImageView) itemView.findViewById(android.R.id.icon);
            marginBottom = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).bottomMargin;
            button1 = (Button) itemView.findViewById(android.R.id.button1);
            button2 = (Button) itemView.findViewById(android.R.id.button2);
        }
    }
    private class Adapter extends RecyclerView.Adapter<TwoLinesViewHolder> {
        private final Picasso picasso;
        private final int navigationHeight;
        private final Book[] data;
        private final boolean hasAppBarLayout;

        public Adapter(final Context context, final int navigationHeight, final boolean hasAppBarLayout, final Book[] data) {
            this.navigationHeight = navigationHeight;
            this.data = data;
            this.hasAppBarLayout = hasAppBarLayout;
            this.picasso = Picasso.with(context);
        }
        @Override
        public TwoLinesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = LayoutInflater.from(getContext()).inflate(R.layout.simple_card_item, parent, false);
            final TwoLinesViewHolder holder = new TwoLinesViewHolder(view);

            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Snackbar snackbar =
                            Snackbar.make(mRoot, "Button 1 of item " + holder.getAdapterPosition(), Snackbar.LENGTH_LONG)
                                    .setAction(
                                            "Action",
                                            null
                                    );
                    snackbar.show();
                }
            });

            holder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Snackbar snackbar = Snackbar.make(mRoot, "Button 2 of item " + holder.getAdapterPosition(),
                            Snackbar.LENGTH_LONG
                    )
                            .setAction(
                                    "Action",
                                    null
                            );
                    snackbar.show();
                }
            });

            return holder;
        }
        @Override
        public void onBindViewHolder(final TwoLinesViewHolder holder, final int position) {
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = 0;
            if (position == getItemCount() - 1) {
                ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom + navigationHeight;
            } else if (position == 0 && !hasAppBarLayout) {
                ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = scrollHelper.getToolbarHeight();
            } else {
                ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom;
            }

            final Book item = data[position];
            holder.title.setText(item.title);
            holder.description.setText("By " + item.author);
            holder.imageView.setImageBitmap(null);

            picasso.cancelRequest(holder.imageView);

            picasso
                    .load(item.imageUrl)
                    .noPlaceholder()
                    .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
                    .centerCrop()
                    .into(holder.imageView);
        }
        @Override
        public int getItemCount() {
            return data.length;
        }
    }
    private Book[] createData() {
        return new Book[]{
                new Book("The Flight", "Scott Masterson", "http://i.imgur.com/dyyP2iO.jpg"),
                new Book("Room of Plates", "Ali Conners", "http://i.imgur.com/da6QIlR.jpg"),
                new Book("The Sleek Boot", "Sandra Adams", "http://i.imgur.com/YHoOJh4.jpg"),
                new Book("Night Hunting", "Janet Perkins", "http://i.imgur.com/3jxqrKP.jpg"),
                new Book("Rain and Coffee", "Peter Carlsson", "http://i.imgur.com/AZRynvM.jpg"),
                new Book("Ocean View", "Trevor Hansen", "http://i.imgur.com/IvhOJcw.jpg"),
                new Book("Lovers Of The Roof", "Britta Holt", "http://i.imgur.com/pxgI1b4.png"),
                new Book("Lessons from Delhi", "Mary Johnson", "http://i.imgur.com/oT1WYX9.jpg"),
                new Book("Mountaineers", "Abbey Christensen", "http://i.imgur.com/CLLDz.jpg"),
                new Book("Plains In The Night", "David Park", "http://i.imgur.com/7MrSvXE.jpg?1"),
                new Book("Dear Olivia", "Sylvia Sorensen", "http://i.imgur.com/3mkUuux.jpg"),
                new Book("Driving Lessons", "Halime Carver", "http://i.imgur.com/LzYAfFL.jpg"),
        };
    }
    static class Book {
        final String title;
        final String author;
        final String imageUrl;

        Book(final String title, final String author, final String imageUrl) {
            this.title = title;
            this.author = author;
            this.imageUrl = imageUrl;
        }
    }
}