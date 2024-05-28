package b_oop.d_packages.packageDriver;

import b_oop.d_packages.packageA.ClassA;
import b_oop.d_packages.packageA.ClassB;
import b_oop.d_packages.packageA.ClassC;
import b_oop.d_packages.packageB.ClassD;
import b_oop.d_packages.packageB.ClassE;

public class Driver {
    public static void main(String[] args) {

        ClassB classB = new ClassB(1, "B", "classB");
        classB.showInfo();
        classB.setPath("initial path");
        classB.updatePath("pathB");
        classB.showInfo();

        ClassA classA = new ClassA(1, "A");
        classA.setPath("initial path");
        ClassC classC = new ClassC("classC");
        classC.showInfo(classA);

        ClassD classD = new ClassD(1,"D", "classD");
        classD.showInfo();
        classD.setPath("initial path");
        classD.updatePath("pathD");

        ClassE classE = new ClassE("classE");
        classE.showInfo(classA);
    }
}
