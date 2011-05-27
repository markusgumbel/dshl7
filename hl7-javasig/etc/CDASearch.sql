SELECT  observation.value_number, observation.value_unit, observation.code_displayname , doc.effectivetime_low
FROM ACT doc
INNER JOIN ACTRELATIONSHIP actrel ON (doc.internalid = actrel.sourceinternalid)
INNER JOIN ACT body ON(actrel.targetinternalid = body.internalid)
INNER JOIN ACTRELATIONSHIP component ON(body.internalid = component.sourceinternalid)
INNER JOIN ACT section ON(component.targetinternalid = section.internalid)
INNER JOIN ACTRELATIONSHIP entry ON (entry.sourceinternalid = section.internalid)
INNER JOIN ACT observation ON (entry.targetinternalid = observation.internalid)

INNER JOIN Participation subject ON(doc.internalId = subject.actInternalId)
INNER JOIN Role_id mrn ON(mrn.roleInternalId = subject.roleInternalId)
WHERE subject.typeCode IN ('SBJ', 'RCT')
AND mrn.root ='2.16.840.1.113883.19.5'
AND mrn.extension =  '12345'