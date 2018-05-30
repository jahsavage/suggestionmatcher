package com.discoid.test.autocomplete.di.inject;

/**
 * Interfaces for classes providing a component.
 */
public interface ComponentProvider<C> {

    /**
     * @return Component provided by this class.
     */
    C getComponent();

}
