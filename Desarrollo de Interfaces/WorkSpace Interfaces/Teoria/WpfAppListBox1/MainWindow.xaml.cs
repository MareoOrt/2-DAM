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

namespace WpfAppListBox1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            //puedo asigna propiedades por código
            lbox1.SelectionMode = SelectionMode.Single;
            lbox2.SelectionMode = SelectionMode.Extended;

            Persona persona = new Persona();
            //persona.Name = txtNombre.Text ;
            persona.MyProperty = "bbbbb";
            //Personas.Add(persona);

            //lbox1.Items.Add(persona);


            cb1.Items.Add("Edad");
            cb1.Items.Add("Name");
            cb1.Items.Add("MyPropyety");
            cb1.SelectedIndex = 0;

        }
        List<int> list2 = new List<int> { 4, 5, 7, 4, 3 };
        List<Persona> Personas = new List<Persona>
        {
            new Persona {Name="Pablo",
                Edad=new DateTime(2002,05,26),
                Id=1,
                MyProperty="dePablo"},
            new Persona {Name="Luis",
                Edad=new DateTime(2002,05,26),
                Id=2,
                MyProperty=""},
            new Persona {Name="Ana",
                Edad=new DateTime(2002,05,26),
                Id=3,
                MyProperty="deAna"}
        };
        List<string> lista = new List<string>
        { "A", "B", "C", "D", "E", "F" }; //colección de elementos
        List<int> indices = new List<int>(); //colección de índices

        private void botonCargar_Click(object sender, RoutedEventArgs e)
        {

            if (lbox1.Items.Count == 0)
            {
                // lleno el listbox1 con los elementos de la lista
                // recorro con un bucle la lista
                lbox1.Items.Clear(); // limpiar la lista cada vez
                foreach (Persona n in lista)
                {
                    lbox1.Items.Add("")
                }
                lbox11.DisplayMemberPath = "Id";
                // lbox1.SelectedValuePath = "Name";

            }
        }

        private void lbox1_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (!lbox2.Items.Contains(lbox1.SelectedItem))
            {
                // lbox2.Items.Add(lbox1.SelectedItem); //(*)
                // lbox2.Items.Add((Persona)lbox1.SelectedItem as Persona);
                // lbox2.Items.Add(((Persona)lbox1.SelectedItem).Name);
                lbox2.Items.Add(lbox1.SeletedValue);
                tb1.Text = lbox1.SelectedIndex.ToString();
                //(*)
                // e.AddedItems contiene la colección de elementos seleccionados:
                // lbox2.Items.Add(e.AddedItems[0]);
            }
        }

        private void lbox2_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (lbox2.SelectedIndex >= 0)
            {
                indices.Add(lbox2.SelectedIndex);
                ActualizarIndices();
            }
        }

        private void ActualizarIndices()
        {
            tb2.Text = string.Empty;
            foreach (var item in lbox2.SelectedItems)
            {
                tb2.Text += $"{lbox2.Items.IndexOf(item)}, ";
            }
        }

        public void cb1_SelectionChanged()
        {

            // lbox1.SelectedValuePath = cb1.Text;
            lbox1.SelectedValuePath = null;// "refreso" el tipo para valuePath
            lbox1.SelectedValuePath = cb1.SelectedItem.ToString();
        }
    }
    public class Persona
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public DateTime Edad { get; set; }

        public int OtraProp { get; set; }

        private double miDoble;

        public double MiDoble
        {
            get { return miDoble; }
            set { miDoble = value; }
        }


        private string myVar;

        public string MyProperty // oPersona.MyProperty=txtPropiedad.Text, en Java opersona.SetProperty("valor")
        {
            get { return myVar; }
            set
            {
                if (value.Length == 0)
                {
                    value = "NO ASIGNADO";
                }
                myVar = value;
            }
        }

    }

}
