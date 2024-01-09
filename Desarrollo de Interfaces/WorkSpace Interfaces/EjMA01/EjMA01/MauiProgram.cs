using EjMA01.Pages;
using EjMA01.ViewModel;
using Microsoft.Extensions.Logging;

namespace EjMA01
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                    fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
                });

            builder.Services.AddSingleton<MainPageVM>();
            builder.Services.AddSingleton<MainPage>();
            builder.Services.AddTransient<NewPage1>();
            builder.Services.AddTransient<NewPage2>();

#if DEBUG
            builder.Logging.AddDebug();
#endif

            return builder.Build();
        }
    }
}
