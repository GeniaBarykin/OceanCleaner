package nl.saxion.playground.template.oceancleanup.сonstraint;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import nl.saxion.playground.template.R;

/**
 * Show death menu
 *
 * @author genia
 */
public class ConstraintDeathMenu extends ConstraintLayout {
    public ConstraintDeathMenu(Context context) {
        super(context);
        init();
    }

    public ConstraintDeathMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConstraintDeathMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.constraint_death_menu, this);
    }
}
