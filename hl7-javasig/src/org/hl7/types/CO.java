package org.hl7.types;

public interface CO extends CV {
    BL  lessOrEqual(CO o);
    BL  lessThan(CO o);
    BL  greaterThan(CO o);
    BL  greaterOrEqual(CO o);
}
