package org.hl7.types;

/**
 * BN constrains the boolean type so that the value may not be NULL. This type
 * is created for use within the data types specification where it is not
 * appropriate for a null value to be used.
 */
public interface BN extends BL {
    BN isNull();
}
