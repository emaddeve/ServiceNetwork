cd personlib
ant clear
ant dist
cp dist/persons.jar ../webapp/lib
cd ../servicelib
ant clear
ant dist
cp dist/services.jar ../webapp/lib
cd ../webapp
ant clear
ant deploy


