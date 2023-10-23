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

namespace WpfLinQ0
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            
            // Create the first data source.
            List<Student> students = new List<Student>()
        {
            new Student { First="Svetlana",
                Last="Omelchenko",
                ID=111,
                Street="123 Main Street",
                City="Seattle",
                Scores= new List<int> { 97, 92, 81, 60 } },
            new Student { First="Claire",
                Last="O’Donnell",
                ID=112,
                Street="124 Main Street",
                City="Redmond",
                Scores= new List<int> { 75, 84, 91, 39 } },
            new Student { First="Sven",
                Last="Mortensen",
                ID=113,
                Street="125 Main Street",
                City="Lake City",
                Scores= new List<int> { 88, 94, 65, 91 } },
            new Student { First="Nicasio",
                City="PucelaCity",
                ID=8888,
                Last="Pérez",
                Street="C/ de la Calle, 64",
                Scores=new List<int> {100,20,10}},
        };
            Student estudiante  = new Student();
            estudiante.First = "Anacleto";
            estudiante.City = "Palencia";

            students.Add(estudiante);

            // Create the second data source.
            List<Teacher> teachers = new List<Teacher>()
        {
            new Teacher { First="Ann", Last="Beebe", ID=945, City="Seattle" },
            new Teacher { First="Alex", Last="Robinson", ID=956, City="Redmond" },
            new Teacher { First="Michiyo", Last="Sato", ID=972, City="Tacoma" }
        };

            // Create the query.
            var peopleInSeattle = (from student in students
                                   where student.City == "PucelaCity"
                                   select $"{student.Last}, {student.First}");

            // Execute the query.
            foreach (var person in peopleInSeattle)
            {
                lbTodoSeatle.Items.Add(person);
            }

            Console.WriteLine("Press any key to exit.");
        }

        private void lbTodoSeatle_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Student estudianteSeleccionado = (Student) lbTodoSeatle.SelectedItem;
            MessageBox.Show($"domicilio:\n {estudianteSeleccionado.Street}");
        }

        /* Output:
            The following students and teachers live in Seattle:
            Omelchenko
            Beebe
         */
    }

    class Student
    {
        public string First { get; set; }
        public string Last { get; set; }
        public int ID { get; set; }
        public string Street { get; set; }
        public string City { get; set; }
        public List<int> Scores;

        public override string ToString()
        {
            return $"{this.Last} {this.First}";
        }
    }

    class Teacher
    {
        public string First { get; set; }
        public string Last { get; set; }
        public int ID { get; set; }
        public string City { get; set; }
    }
}
