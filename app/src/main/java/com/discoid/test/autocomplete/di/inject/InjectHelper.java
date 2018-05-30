package com.discoid.test.autocomplete.di.inject;

import android.content.Context;

import com.discoid.test.autocomplete.di.containers.ApplicationComponent;

/**
 * Helper methods related to dependency injection.
 */
public final class InjectHelper {

    private InjectHelper() {
    }

    /**
     * Returns component provided by given Activity.
     * @param componentType Class of component
     * @param componentProvider Object providing the component must be implementing {@code
     * ComponentProvider}
     * @param <C> Type of component
     * @return Component instance
     */
    @SuppressWarnings("unchecked")
    public static <C> C getComponent(Class<C> componentType, Object componentProvider) {
        C component = ((ComponentProvider<C>) componentProvider).getComponent();
        return componentType.cast(component);
    }

    public static ApplicationComponent getApplicationComponent(Context context) {
        return getComponent(ApplicationComponent.class, context.getApplicationContext());
    }

}
