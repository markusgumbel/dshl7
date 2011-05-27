package org.hl7.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hl7.types.enums.AddressPartType;
import org.hl7.types.enums.EntityNamePartType;
import org.hl7.types.impl.ADXPimpl;
import org.hl7.types.impl.ADimpl;
import org.hl7.types.impl.BAGjuListAdapter;
import org.hl7.types.impl.DSETnull;
import org.hl7.types.impl.ENXPimpl;
import org.hl7.types.impl.ENXPnull;
import org.hl7.types.impl.ENimpl;
import org.hl7.types.impl.STjlStringAdapter;

public class DatatypeTool {

    /**
     * Utility methods for {@link EN}.
     */
    public static class EntityNameTool {
        /**
         * Extract a specific name part type from a collection of names.
         *
         * @param names collection of entity names (must not be
         *              <code>null</code>)
         * @param type  desired name part type
         * @return the value of an {@link ENXP} which matches the desired
         *         <code>type</code> (if there are several matches any one
         *         could be returned), or <code>null</code> if no match is
         *         found.
         */
        private static String getNamePart(final BAG<EN> names,
                                          final EntityNamePartType type) {
            for (final EN en : names) {
                for (final ENXP part : en) {
                    if (part.type().implies(type).isTrue()) {
                        return part.toString();
                    }
                }
            }
            return null;
        }

        public static String getGivenName(final BAG<EN> name) {
            return getNamePart(name, EntityNamePartType.Given);
        }

        public static String getFamilyName(final BAG<EN> name) {
            return getNamePart(name, EntityNamePartType.Family);
        }

        public static String getTrivialName(final BAG<EN> name) {
            for (final EN en : name) {
                for (final ENXP part : en) {
                    if (part.type().isNull().isTrue()) {
                        return part.toString();
                    }
                }
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        private static BAG<EN> setNamePart(final BAG<EN> names,
                                           final String namePart, final EntityNamePartType type) {
            final List<ENXP> enxpList = new ArrayList<ENXP>();
            final Collection<EN> data = new ArrayList<EN>();

            if (names == null) {
                enxpList.add(ENXPimpl.valueOf(namePart, type, (DSET<CS>) DSETnull.NA));
            } else {
                boolean foundNamePart = false;
                for (final EN next : names) {
                    for (final ENXP part : next) {
                        if (part.type().implies(type).isTrue()) {
                            enxpList.add(ENXPimpl.valueOf(namePart,
                                    (EntityNamePartType) part.type(), part
                                            .qualifier()));
                            foundNamePart = true;
                        } else {
                            enxpList.add(part);
                        }
                    }
                    if (!foundNamePart) {
                        enxpList.add(ENXPimpl.valueOf(namePart, type,
                                (DSET<CS>) DSETnull.NA));
                    }
                }
            }
            data.add(ENimpl.valueOf(enxpList));
            return BAGjuListAdapter.valueOf(data);
        }

        public static BAG<EN> setPrefixName(final BAG<EN> name,
                                            final String prefixName) {
            return setNamePart(name, prefixName, EntityNamePartType.Prefix);
        }

        public static BAG<EN> setSuffixName(final BAG<EN> name,
                                            final String suffixName) {
            return setNamePart(name, suffixName, EntityNamePartType.Suffix);
        }

        public static BAG<EN> setGivenName(final BAG<EN> name,
                                           final String givenName) {
            return setNamePart(name, givenName, EntityNamePartType.Given);
        }

        public static BAG<EN> setFamilyName(final BAG<EN> name,
                                            final String familyName) {
            return setNamePart(name, familyName, EntityNamePartType.Family);
        }
    }

    public static class ENTool {
        private static ENXP getPart(final EN name, final EntityNamePartType type) {
            for (final ENXP part : name) {
                if (part.type().implies(type).isTrue()) {
                    return part;
                }
            }
            return ENXPnull.NI;
        }

        public static ENXP getGivenName(final EN name) {
            return getPart(name, EntityNamePartType.Given);
        }

        public static ENXP getFamilyName(final EN name) {
            return getPart(name, EntityNamePartType.Family);
        }

        public static ENXP getTrivialName(final EN name) {
            for (final ENXP part : name) {
                if (part.type().isNull().isTrue()) {
                    return part;
                }
            }
            return ENXPnull.NI;
        }

        // those are used for the Hibernate XMLWithIndexableColumns ...

        public static ST getFAMILYNAME(final EN name) {
            final ENXP result = getFamilyName(name);
            if (result.nonNull().isTrue()) {
                return STjlStringAdapter.valueOf(result.toString()
                        .toUpperCase());
            } else {
                return result;
            }
        }

        public static ST getGIVENNAME(final EN name) {
            final ENXP result = getGivenName(name);
            if (result.nonNull().isTrue()) {
                return STjlStringAdapter.valueOf(result.toString()
                        .toUpperCase());
            } else {
                return result;
            }
        }

        public static ST getTRIVIALNAME(final EN name) {
            final ENXP result = getTrivialName(name);
            if (result.nonNull().isTrue()) {
                return STjlStringAdapter.valueOf(result.toString()
                        .toUpperCase());
            } else {
                return result;
            }
        }
    }

    public static class ANYTool {
        public static ST getDisplayName(final ANY value) {
            // Prefer not to do this here, because it gives a wrong impression
            // as if there was only one value
            // got it fixed in the WordIndexer on my side.
            // if(value instanceof DSET) // if we have multiple values in a set,
            // take one representative for indexing
            // value = ((DSET)value).any(); // XXX: the semantics of any() is
            // not guaranteed to yield the same each time

            if (value instanceof CD) {
                final CD cd = (CD) value;
                final ST displayname = cd.displayName();
                return displayname;
            } else {
                return null;
            }
        }

        public static ST getCode(final ANY value) {
            // if(value instanceof DSET) // if we have multiple values in a set,
            // take one representative for indexing
            // value = ((DSET)value).any();

            if (value instanceof CD) {
                final CD cd = (CD) value;
                final ST code = cd.code();
                return code;
            } else {
                return null;
            }
        }

        public static ST getCodeSystem(final ANY value) {
            // if(value instanceof DSET) // if we have multiple values in a set,
            // take one representative for indexing
            // value = ((DSET)value).any();

            if (value instanceof CD) {
                final CD cd = (CD) value;
                final ST codesys = cd.codeSystem();
                return codesys;
            } else {
                return null;
            }
        }

        public static ST getPQValue(final ANY value) {
            if (value instanceof PQ) {
                final REAL val = ((PQ) value).value();
                if (val.isNull().isFalse()) {
                    return STjlStringAdapter.valueOf(val.toString());
                }
            }
            return null;
        }

        public static ST getPQUnit(final ANY value) {
            if (value instanceof PQ) {
                return ((PQ) value).unit().code();
            }
            return null;
        }

        public static ST getPQCanonicalValue(final ANY value) {
            if (value instanceof PQ) {
                final REAL val = ((PQ) value).canonical().value();
                if (val.isNull().isFalse()) {
                    return org.hl7.types.impl.STjlStringAdapter.valueOf(val
                            .toString());
                }
            }
            return null;
        }

        public static ST getPQCanonicalUnit(final ANY value) {
            if (value instanceof PQ) {
                return ((PQ) value).canonical().unit().code();
            }
            return null;
        }

        public static ST getRTONumeratorPQCanonicalValue(final ANY value) {
            if (value instanceof RTO) {
                return getPQCanonicalValue(((RTO) value).numerator());
            } else {
                return null;
            }
        }

        public static ST getRTONumeratorPQCanonicalUnit(final ANY value) {
            if (value instanceof RTO) {
                return getPQCanonicalUnit(((RTO) value).numerator());
            } else {
                return null;
            }
        }

        public static ST getRTODenominatorPQCanonicalValue(final ANY value) {
            if (value instanceof RTO) {
                return getPQCanonicalValue(((RTO) value).denominator());
            } else {
                return null;
            }
        }

        public static ST getRTODenominatorPQCanonicalUnit(final ANY value) {
            if (value instanceof RTO) {
                return getPQCanonicalUnit(((RTO) value).denominator());
            } else {
                return null;
            }
        }

    }

    public static class GTSTool {
        public static TS getLowBoundaryOfHull(final SET<TS> value) {
            if (value != null && value.nonNull().isTrue()
                    && value instanceof QSET) {
                final QTY result = ((QSET<TS>) value).hull().low();
                if (result instanceof TS) {
                    return (TS) result;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        public static TS getHighBoundaryOfHull(final SET<TS> value) {
            if (value != null && value.nonNull().isTrue()
                    && value instanceof QSET) {
                final QTY result = ((QSET<TS>) value).hull().high();
                if (result instanceof TS) {
                    return (TS) result;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public static class AddressTool {
        public static String getAll(final BAG<AD> address) {
            if (address == null) {
                return "";
            }
            final StringBuilder sb = new StringBuilder();
            for (final AD element : address) {
                for (final ADXP element2 : element) {
                    if (element2.nonNull().isTrue()) {
                        sb.append(element2).append(' ');
                    }
                }
            }
            return sb.toString();
        }

        private static BAG<AD> setPart(final BAG<AD> addresses,
                                       final String partName, final AddressPartType type) {
            final List<ADXP> adxpList = new ArrayList<ADXP>();
            final Collection<AD> data = new ArrayList<AD>();
            boolean found = false;
            for (final AD next : addresses) {
                for (final ADXP part : next) {
                    if (part.type().implies(type).isTrue()) {
                        adxpList.add(ADXPimpl.valueOf(partName,
                                (AddressPartType) part.type()));
                        found = true;
                    } else {
                        adxpList.add(part);
                    }
                }
                if (!found) {
                    adxpList.add(ADXPimpl.valueOf(partName, type));
                }
            }
            data.add(ADimpl.valueOf(adxpList));
            return BAGjuListAdapter.valueOf(data);
        }

        public static BAG<AD> setStreetAddress(final BAG<AD> streetBag, final String street) {
            return setPart(streetBag, street, AddressPartType.StreetAddressLine);
        }

        public static BAG<AD> setMunicipality(final BAG<AD> municipalityBag,
                                              final String city) {
            return setPart(municipalityBag, city, AddressPartType.Municipality);
        }

        public static BAG<AD> setPostalCode(final BAG<AD> postalBag,
                                            final String postalCode) {
            return setPart(postalBag, postalCode, AddressPartType.PostalCode);
        }

        public static BAG<AD> setStateOrProvince(final BAG<AD> stateBag,
                                                 final String stateOrProvince) {
            return setPart(stateBag, stateOrProvince, AddressPartType.StateOrProvince);
        }

        public static BAG<AD> setAll(final BAG<AD> address,
                                     final String newAddress) {
            final String[] addresses = newAddress.split(" ");
            final Collection<AD> adCollection = new ArrayList<AD>();
            final List<ADXP> adxpList = new ArrayList<ADXP>();
            for (final String s : addresses) {
                adxpList.add(ADXPimpl.valueOf(s));
            }
            adCollection.add(ADimpl.valueOf(adxpList));
            return BAGjuListAdapter.valueOf(adCollection);
        }
    }
}
