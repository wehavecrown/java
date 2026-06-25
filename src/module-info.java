/**
 * Module descriptor for Java 9+ module system.
 * Defines module dependencies and exports packages.
 */
module com.java {
    // Requires other modules
    requires java.base;  // Implicitly required
    requires java.sql;
    requires java.desktop;

    // Exports packages to other modules
    exports com.java.basics;
    exports com.java.oop;
    exports com.java.exceptions;
    exports com.java.lambdas;
    exports com.java.multithreading;
    exports com.java.collections;
    exports com.java.streams;
    exports com.java.jvm;

    // Opens packages for reflection
    // opens com.java.basics to module.name;
}