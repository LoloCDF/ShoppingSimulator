##################################
# Compilation and execution      #
##################################

runandcomp:
	make comp && make exe

##################################
# Compilation ShoppingSimulator  #
##################################

comp:
	find src/ -name "*.java" -print | xargs javac -d build
	jar cvfe ShoppingSimulator.jar ShoppingSimulator.Main -C build ShoppingSimulator

##################################
# Execution ShoppingSimulator    #
##################################
exe:
	java -cp ../common/ShoppingSimulatorCommon.jar:ShoppingSimulator.jar -Djava.security.policy=server.policy -Djava.net.preferIPv4Stack=true ShoppingSimulator.Main
