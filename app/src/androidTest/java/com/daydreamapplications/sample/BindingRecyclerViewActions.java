package com.daydreamapplications.sample;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.daydreamapplications.bindingrecycler.BindingRecyclerView;

import org.hamcrest.Matcher;

import java.lang.reflect.Field;

import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.*;


public class BindingRecyclerViewActions {

    /**
     * Returns a {@link ViewAction} which scrolls {@link RecyclerView} to the view matched by
     * itemViewMatcher.
     * <p>
     * <p>
     * This approach uses {@link RecyclerView.ViewHolder}s to find the target view. It will create one ViewHolder
     * per item type and bind adapter data to the ViewHolder. If the itemViewMatcher matches a
     * ViewHolder the current position of the View is used to perform a
     * {@link RecyclerView#scrollToPosition(int)}.
     * </p>
     *
     * @param itemViewMatcher a
     *                        <a href="http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matcher.html">
     *                        <code>Matcher</code></a> that matches an item view in {@link RecyclerView}
//     * @throws PerformException if there are more than one items matching given viewHolderMatcher.
     */
    public static ViewAction[] scrollToMatchingItemAtPosition(
            final int position,
            final Matcher<View> itemViewMatcher
    ) {

        return new ViewAction[]{
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
                    }

                    @Override
                    public String getDescription() {
                        return null;
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        if (((RecyclerView) view).getAdapter() instanceof BindingRecyclerView.Adapter) {
                            try {
                                Field f = RecyclerView.class.getDeclaredField("mLayoutOrScrollCounter");
                                f.setAccessible(true);
                                f.setInt(view, 1);
                            } catch (Throwable e) {
                                throw new WrappedException(e);
                            }
                        }
                    }
                },

                RecyclerViewActions.scrollTo(itemViewMatcher).atPosition(position),
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return allOf(isAssignableFrom(RecyclerView.class), isDisplayed());
                    }

                    @Override
                    public String getDescription() {
                        return null;
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        if (((RecyclerView) view).getAdapter() instanceof BindingRecyclerView.Adapter) {
                            try {
                                Field f = RecyclerView.class.getDeclaredField("mLayoutOrScrollCounter");
                                f.setAccessible(true);
                                f.setInt(view, 0);
                                f.setAccessible(false);
                            } catch (Throwable e) {
                                throw new WrappedException(e);
                            }
                        }
                    }
                },
        };
    }
}

