namespace MauiAppEjemCreacionInterfaz
{
    public partial class MainPage : ContentPage
    {
        Button loginButton;
        VerticalStackLayout layout;

        public MainPage()
        {
            this.BackgroundColor = Color.FromArgb("512bdf");

            layout = new VerticalStackLayout
            {
                Margin = new Thickness(15, 15, 15, 15),
                Padding = new Thickness(30, 60, 30, 30),
                Children =
            {
                new Label { Text = "Please log in", FontSize = 30, TextColor = Color.FromRgb(255, 255, 100) },
                new Label { Text = "Username", TextColor = Color.FromRgb(255, 255, 255) },
                new Entry (),
                new Label { Text = "Password", TextColor = Color.FromRgb(255, 255, 255) },
                new Entry { IsPassword = true }
            }
            };

            loginButton = new Button { Text = "Login", BackgroundColor = Color.FromRgb(0, 148, 255) };
            layout.Children.Add(loginButton);

            Content = layout;

            loginButton.Clicked += (sender, e) =>
            {
                Debug.WriteLine("Clicked !");
            };
        }

    }
