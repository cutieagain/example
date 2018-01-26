package com.cutie.design_pattern.explained_simply.abstract_factory;

/**
 * Created by Cutie on 2018/1/23.
 */

/*CPU*/

// class CPU
abstract class CPU {}

// class EmberCPU
class EmberCPU extends CPU {}

// class EnginolaCPU
class EnginolaCPU extends CPU {}

/*MMU*/

// class MMU
abstract class MMU {}

// class EmberMMU
class EmberMMU extends MMU {}

// class EnginolaMMU
class EnginolaMMU extends MMU {}

// class EmberFactory
class EmberToolkit extends AbstractFactory {
    @Override
    public CPU createCPU() {
        System.out.println("EmberToolkit createCPU");
        return new EmberCPU();
    }

    @Override
    public MMU createMMU() {
        System.out.println("EmberToolkit createMMU");
        return new EmberMMU();
    }
}

// class EnginolaFactory
class EnginolaToolkit extends AbstractFactory {
    @Override
    public CPU createCPU() {
        System.out.println("EnginolaToolkit createCPU");
        return new EnginolaCPU();
    }

    @Override
    public MMU createMMU() {
        System.out.println("EnginolaToolkit EnginolaMMU");
        return new EnginolaMMU();
    }
}

enum Architecture {
    ENGINOLA, EMBER
}

abstract class AbstractFactory {
    private static final EmberToolkit EMBER_TOOLKIT = new EmberToolkit();
    private static final EnginolaToolkit ENGINOLA_TOOLKIT = new EnginolaToolkit();

    // Returns a concrete factory object that is an instance of the
    // concrete factory class appropriate for the given architecture.
    static AbstractFactory getFactory(Architecture architecture) {
        AbstractFactory factory = null;
        switch (architecture) {
            case ENGINOLA:
                factory = ENGINOLA_TOOLKIT;
                break;
            case EMBER:
                factory = EMBER_TOOLKIT;
                break;
        }
        return factory;
    }

    public abstract CPU createCPU();

    public abstract MMU createMMU();
}

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactory.getFactory(Architecture.EMBER);
        CPU cpu = factory.createCPU();
        System.out.println(cpu);
    }
}