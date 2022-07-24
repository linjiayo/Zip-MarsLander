public class OnBoardComputer implements BurnStream {
    int prevBurn;
    @Override
    public int getNextBurn(DescentEvent status) {
        int burn = 0;
        int velocity = status.getVelocity();
        int altitude = status.getAltitude();
        int target = Math.max(2, (int) (0.8 * Math.sqrt(altitude)));

        if (altitude < 300) {
            if (velocity > target) {
                burn = Math.min(200, velocity - target + 100);
            } else {
                burn = Math.max(0, velocity - target + 100);
            }
        } else if (altitude / (double) velocity < 7) {
            burn = 200;
        } else if (altitude / (double) velocity > 18) {
            burn = 0;
        }  else {
            burn = 100;
        }
        if (velocity == 0 && prevBurn == 100 && burn == 100) {
            burn = Math.max(0, velocity - target + 100);
        }
        System.out.println(burn); /*hack!*/
        prevBurn = burn;
        return burn;
    }
}
