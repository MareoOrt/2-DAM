using EjMA01.Pages;

namespace EjMA01
{
    public partial class AppShell : Shell
    {
        public AppShell()
        {
            InitializeComponent();
            Routing.RegisterRoute(nameof(NewPage1), typeof(NewPage1));
        }
    }
}
