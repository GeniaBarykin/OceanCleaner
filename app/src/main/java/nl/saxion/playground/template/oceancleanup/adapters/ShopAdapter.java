package nl.saxion.playground.template.oceancleanup.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.oceancleanup.model.shop.Upgrade;

/**
 * @author Hakim
 */
public class ShopAdapter extends ArrayAdapter<Upgrade> {

    private LayoutInflater inflater;
    private Upgrade currentUpgrade;

    public ShopAdapter(@NonNull Context context, @NonNull List<Upgrade> objects) {
        super(context, 0, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.shop_item, parent, false);
        }

        ImageView tvItemImage = convertView.findViewById(R.id.ivShopItem);

        TextView tvItemName = convertView.findViewById(R.id.tvItemName);
        TextView tvItemDescription = convertView.findViewById(R.id.tvItemDescription);
        TextView tvItemPrice = convertView.findViewById(R.id.tvItemPrice);

        currentUpgrade = getItem(position);

        tvItemImage.setImageResource(currentUpgrade.getImageId());
        tvItemName.setText(currentUpgrade.getName());
        tvItemDescription.setText(currentUpgrade.getDescription());
        tvItemPrice.setText("$" + currentUpgrade.getCost());

        return convertView;
    }
}
