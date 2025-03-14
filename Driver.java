/*
file name:      Driver.java
Authors:        Alexander Zhelonkin
last modified:  12/8/23

How to run:     java driver
*/

public class Driver {

  public Driver(int n, double p) throws InterruptedException{
      Graph graph = new Graph(n, p);
      AbstractPlayerAlgorithm pursuer = new ExtensionAlgorithm(graph);
      AbstractPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);

      pursuer.chooseStart();
      evader.chooseStart(pursuer.getCurrentVertex());

      GraphDisplay display = new GraphDisplay(graph, pursuer, evader, 40);
      display.repaint();

      // Play the game until the pursuer captures the evader
      while (pursuer.getCurrentVertex() != evader.getCurrentVertex()){
          Thread.sleep(750);
          pursuer.chooseNext(evader.getCurrentVertex());
          display.repaint();
          if (pursuer.getCurrentVertex() != evader.getCurrentVertex()){
              Thread.sleep(750);
              evader.chooseNext(pursuer.getCurrentVertex());
              display.repaint();
          }
      }
  }

  public static void main(String[] args) throws InterruptedException{
      int vertices = 8;
      double probability = .3;
      new Driver(vertices, probability);
  }
}