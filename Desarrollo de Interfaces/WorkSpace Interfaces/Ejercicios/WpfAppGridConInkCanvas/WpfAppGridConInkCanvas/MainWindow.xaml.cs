using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfAppGridConInkCanvas
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        // Metodo escribir linea con color aleatorio con el click izquierdo
        private void InkCanvas_PreviewMouseDown(object sender, MouseButtonEventArgs e)
        {
            Random randonGen = new Random();
            Color randomColor = Color.FromRgb((byte)randonGen.Next(255), (byte)randonGen.Next(255),
            (byte)randonGen.Next(255));

            Line linea = new Line()
            {
                X1 = Mouse.GetPosition(canvas).X,
                Y1 = Mouse.GetPosition(canvas).Y,
                X2 = Mouse.GetPosition(canvas).X,
                Y2 = Mouse.GetPosition(canvas).Y,
                Stroke = new SolidColorBrush(randomColor)
            };
            canvas.Children.Add(linea);
        }

        // Metodo borrar lineas con click derecho
        private void resetColores(object sender, MouseButtonEventArgs e)
        {
            Boolean imagen = true;
            foreach (var item in canvas.Children)
            {
                if (!imagen)
                {
                    canvas.Children.Remove((UIElement)item);
                }
                else
                {
                    imagen = false;
                }

            }
        }
    }

}
