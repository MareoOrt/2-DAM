using System;
using System.Collections;
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

namespace Proeycto1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        string[] palabras = { "Berengena", "Botafumeiro", "Verde", "Sinuoso" };
        int turno = 0;
        String[] letters;

        public MainWindow()
        {
            
            InitializeComponent();
            rellenarCadena();
            

        }
        public void rellenarCadena()
        {
            foreach(string palabra in palabras)
            {
                lbPalabras.Items.Add(palabra);
            }
        }

        private void botSiguiente_Click(object sender, RoutedEventArgs e)
        {
            turno++;
            if(turno >= letters.Length)
            {
                turno = 0;
            }

            String word = palabras.ElementAt(turno);
            letters = word.Split("");

            escribirTBK("1");
        }

        private void tbxLetra_TextChanged(object sender, TextChangedEventArgs e)
        {
            String letra = tbxLetra.Text;
            escribirTBK(letra);
        }

        public void escribirTBK(String letra)
        {
            String actTBK = ""; 
            foreach (String letter in letters)
            {
                if (letter.Equals(letra, StringComparison.OrdinalIgnoreCase))
                {
                    actTBK = actTBK + letra + " ";
                }
                else
                {
                    actTBK = actTBK + "- ";
                }
            }
            tbkPalabra.Text = actTBK;
        }
    }
}
