package aspects;

import figures.FigureElement;

public aspect Exercise1d implements Observer perthis(execution(figures.Group.new(..))){
       public figures.gui.FigureSurface canvas;

       pointcut adds(figures.FigureElement fe) :
               execution(* figures.Group.add(figures.FigureElement)) && this(fe);

       after(figures.FigureElement fe):adds(fe){
               fe.addObserver(this);
       }
       
       after(figures.FigureElement fe):execution(* figures.FigureElement.*(..)) && this(fe){
               update(fe);
       }

       @Override
       public void update(FigureElement fe) {
               canvas.repaint();
       }
}
