package fr.wallforfry.bdesapp.ViewHolder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.wallforfry.bdesapp.Adapter.NewsViewAdapter;
import fr.wallforfry.bdesapp.Object.CardBigPictureObject;
import fr.wallforfry.bdesapp.Object.CardGameObject;
import fr.wallforfry.bdesapp.R;

/**
 * Created by wallerand on 15/11/2015.
 */
public class CardBigPictureViewHolder extends RecyclerView.ViewHolder{

    private TextView contenu;
    private TextView title;
    private TextView subtitle;
    private ImageView imageView;
    private ImageButton expend;
    private Boolean open = false;
    private CardBigPictureObject myObject;

    //itemView est la vue correspondante à 1 cellule
    public CardBigPictureViewHolder(final View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        title = (TextView) itemView.findViewById(R.id.title);
        subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        contenu = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        expend = (ImageButton) itemView.findViewById(R.id.expend);
        expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open == false) {
                    moreContent(myObject);
                }
                else{
                    lessContent(myObject);
                }
            }
        });
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(CardBigPictureObject myObject){
        this.myObject = myObject;
        title.setText(myObject.getTitle());
        subtitle.setText(myObject.getSubtitle());
        contenu.setVisibility(View.INVISIBLE);
        expend.setImageResource(R.drawable.ic_expand_less_black_24dp);
        //Picasso.with(imageView.getContext()).load(myObject.getImageUrl()).centerCrop().fit().into(imageView);
        imageView.setImageResource(myObject.getImageUrl());
    }

    public void moreContent(CardBigPictureObject myObject){
        contenu.setText(myObject.getText());
        contenu.setVisibility(View.VISIBLE);
        contenu.setPadding(0,16,0,24);
        expend.setImageResource(R.drawable.ic_expand_more_black_24dp);
        open = true;
    }

    public void lessContent(CardBigPictureObject myObject){
        contenu.setText("");
        contenu.setVisibility(View.INVISIBLE);
        contenu.setPadding(0,8,0,0);
        expend.setImageResource(R.drawable.ic_expand_less_black_24dp);
        open = false;
    }
}