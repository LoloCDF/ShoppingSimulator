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
	java -Djava.security.policy=server.policy  -Djava.net.preferIPv4Stack=true -Djava.rmi.server.hostname=localhost -jar ShoppingSimulatorServer.jar


rmiexe:
	rmiregistry -J-Djava.net.preferIPv4Stack=true 8888 &
