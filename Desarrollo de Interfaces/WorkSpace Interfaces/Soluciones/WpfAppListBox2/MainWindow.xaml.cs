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

namespace WpfAppListBox2
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

        List<string> lista = new List<string> { "A", "B", "C", "D", "E", "F" };
        private void botonCargar_Click(object sender, RoutedEventArgs e)
        {
            // lleno el listbox1 con los elementos de la lista
            // recorro con un bucle la lista
            lbox1.Items.Clear(); // limpiar la lista cada vez
            foreach (string n in lista)
            {
                lbox1.Items.Add(n);
            }
        }

        private void botonMover_Click(object sender, RoutedEventArgs e)
        {
            if (lbox1.SelectionMode == SelectionMode.Single)
            {
                lbox2.Items.Add(lbox1.SelectedItem);
            }
            else
            {
                MoverSeleccionados(lbox1, lbox2);
            }
        }

        private void botonRecup_Click(object sender, RoutedEventArgs e)
        {
            MoverSeleccionados(lbox2, lbox1);
        }

        void MoverSeleccionados(ListBox lbOrigen, ListBox lbDestino)
        {
            string[] aux = new string[lbOrigen.SelectedItems.Count];
            lbOrigen.SelectedItems.CopyTo(aux, 0);
            foreach (string n in aux)
            {
                lbDestino.Items.Add(n);
                lbOrigen.Items.Remove(n);
            }
        }
    }

}
