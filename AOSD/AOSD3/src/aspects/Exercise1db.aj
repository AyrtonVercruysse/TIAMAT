package aspects;

public aspect Exercise1db {
       pointcut creation(): execution(figures.gui.FigureSurface.new(..));

       after(figures.gui.FigureSurface fs): creation() && this(fs){
               Exercise1d.aspectOf(fs.getCanvas()).canvas = fs;
       }
}
