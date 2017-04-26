##################################
# Compila y ejecuta              #
##################################

runandcomp:
	make comp && make exe

##################################
# Compila ShoppingSimulator      #
##################################

comp:
	find src/ -name "*.java" -print | xargs javac -d build
	jar cvfe ShoppingSimulatorServer.jar ShoppingSimulator.Main -C build ShoppingSimulator

##################################
# Ejecuta ShoppingSimulator      #
##################################
exe:
	java -cp ../common/ShoppingSimulatorCommon.jar:ShoppingSimulatorServer.jar -Djava.security.policy=server.policy  -Djava.net.preferIPv4Stack=true -Djava.rmi.server.hostname=localhost ShoppingSimulator.Main


rmiexe:
	rmiregistry -J-Djava.net.preferIPv4Stack=true 8888 &
