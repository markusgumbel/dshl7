Here are the changes compared to the Regenstrief-Version.

Web page: http://aurora.regenstrief.org/javasig

Based on Regenstrief nightly build as of 2010-04-11:
http://aurora.regenstrief.org/~javasig/hl7-javasig-7-20100411.zip


HL7-CD-ROM adaption.
The build.xml was adapted to point to a HL7-CD. However, my distribution
(source unknown) does not have a mif.zip file. This was added to my CD-distribution.
A mif.zip can be found at http://gforge.hl7.org/gf/project/mif-schemas/frs/
or more directly under http://gforge.hl7.org/gf/download/frsrelease/603/6640/hl7_mifschemas-2.1.6.0.zip

Misc.
* org.hl7.types.imp.REALdoubleAdapter (Java) needs to be patched at line 531
* org.hl7.demo.CDAMaker (Groovy) is not completed
* A units.xml is required in to in the class path. This file is created
 at build time. I copied it to ./resources/units.xml and added ./resources
 to the class path.

Today (28.2.11) I copied the rim.coremif and the MIF schemas to the tmp directory.
This was needed to be able to generate the RIM Java classes.
rim.coremif contains the specification (structure) of the RIM classes.