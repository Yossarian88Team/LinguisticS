/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzycomponents;

/**
 *
 * @author wlochaty
 */
public class FuzzySetType2 {
    
    private FuzzySet fSet; //główny zbiór rozmyty
    private FuzzySpace fSpace; //przestrzen [0, 1]
    private int membershipFunType; //typ drugorzędnej funkcji przynależności
    private double lenghtSpace; //dlugość przedziału od środka
    private FuzzySet fSetUMF = null; //zbiór rozmyty potrzebny do określenia długości nośnika
    

    public FuzzySetType2(FuzzySet fSet, FuzzySpace fSpace, int membershipFunType) {
        this.fSet = fSet;
        this.fSpace = fSpace;
        this.membershipFunType = membershipFunType;
    }
    
    public FuzzySetType2(FuzzySpace fSpace, MembershipFunInter memFunMain, int typeMemSecond, double lenghtSecondSpace)
    {
        this.fSpace = new FuzzySpace(0.0, 1.0, 0.01);
        this.lenghtSpace = lenghtSecondSpace;
        this.fSet = new FuzzySet(fSpace, memFunMain);
        this.membershipFunType = typeMemSecond;
        
    }
    
    public FuzzySetType2(FuzzySet fSet)
    {
        this.fSpace = new FuzzySpace(0.0, 1.0, 0.01);
        this.lenghtSpace = 0;
        this.fSet = new FuzzySet(fSet.getFspace(), fSet.getMemberFun());
        this.membershipFunType = 1;
    }
    
    public FuzzySetType2(FuzzySet fSet, String membershipFunType)
    {
        this.fSpace = new FuzzySpace(0.0, 1.0, 0.01);
        this.lenghtSpace = 0;
        this.fSet = new FuzzySet(fSet.getFspace(), fSet.getMemberFun());
        if(membershipFunType.equalsIgnoreCase("triangular"))
              this.membershipFunType = 1;
        if(membershipFunType.equalsIgnoreCase("rectengular"))
              this.membershipFunType = 2;      
        else this.membershipFunType = 3;
    }
    
    public FuzzySpace getSpaceForFuzzySet()
    {
        return fSet.getFspace();
    }
    
    public double getFuzzySetMembership(double val)
    {
        return fSet.getMemberFun(val);
    }
    
    public MembershipFunInter getMembershipFunFuzzySet()
    {
        return fSet.getMemberFun();
    }
    
    public FuzzySet getSecondMembershipFun(double val)
    {
        double mem = getFuzzySetMembership(val);
        MembershipFunInter SecondMembershipFun = createSecondMemFun(mem);
        
        FuzzySet newCreateFuzzySet = new FuzzySet(fSpace, SecondMembershipFun);
        return newCreateFuzzySet;        
    }
    
    public String getTypeFun()
    {
        if(membershipFunType == 1)
            return MembershipFunInter.triangular;
        else if(membershipFunType == 2)
            return MembershipFunInter.rectengular;        
        else
            return MembershipFunInter.unit;
    }
    
    public MembershipFunInter createSecondMemFun(double center)
    {
        if(membershipFunType == 1)
        {
            return new TriangularMembershipFun(center - lenghtSpace, center, center + lenghtSpace);
        }else if(membershipFunType == 2)
        {
            return new RectengularMembershipFun( center - lenghtSpace, center + lenghtSpace);
        }else
        {
            return new UnitMembershipFun();
        }
    }
    
    public FuzzySet getSetHelp()
    {
        if(fSetUMF == null)
        {
            if(getSecondMembershipFun(0.0).memberFun.ifSharp())
               fSetUMF = fSet;
            else
               fSetUMF = new FuzzySet(fSet.getFspace(), new FuzzySpaceTyp2(fSet.getFspace(), fSet.getMemberFun(), lenghtSpace));        
        }
        return fSetUMF;
    }
    
    public double clm()
    {
        return fSetUMF.getClm();
    }
    
    public double getLenghtSup()
    {
        return getSetHelp().getLenghtSup();
    }
}
