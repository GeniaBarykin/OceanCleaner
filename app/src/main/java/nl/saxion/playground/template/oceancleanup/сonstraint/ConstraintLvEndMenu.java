package nl.saxion.playground.template.oceancleanup.сonstraint;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import nl.saxion.playground.template.R;

/**
 * Show lv evd menu
 *
 * @author genia
 */
public class ConstraintLvEndMenu extends ConstraintLayout {
    public ConstraintLvEndMenu(Context context) {
        super(context);
        init();
    }

    public ConstraintLvEndMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConstraintLvEndMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.constraint_lvend_menu, this);
    }
}
