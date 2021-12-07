package ooga.model.die;

public class DoublesDie extends OriginalDice {


  @Override
  public int roll() {

    int roll = myRandom.nextInt(6) + 1;

    myLastRoll[0] = roll;
    myLastRoll[1] = roll;
    return 2 * roll;
  }

  @Override
  public boolean lastRollDouble() {
    return true;
  }

}
