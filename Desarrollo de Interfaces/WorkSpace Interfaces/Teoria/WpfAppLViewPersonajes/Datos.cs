using Newtonsoft.Json.Linq;
using System;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;

namespace WpfAppLViewPersonajes
{
    internal class Datos
    {
        public static ObservableCollection<Personaje> Personajes { get; set; } = new();

        public static void RecuperarDatos()
        {
            /*
            Deserializando sin más sería necesario eliminar el encabezado "personaje" del archivo .json
            string json = File.ReadAllText("Datos/personajes.json");
            var personajes = JsonConvert.DeserializeObject<List<Personaje>>(json);
            foreach (Personaje p in personajes)
            {
                this.Add(p);
            }
            */
            /*
            Con Linq2Json:
            JObject personajesJS = JObject.Parse(File.ReadAllText("Datos/personajes.json"));
            var personajes = from p in personajesJS["personaje"]
            */
            var personajes = from p in JObject.Parse(File.ReadAllText("Datos/personajes.json")).SelectToken("personaje")
                             select new Personaje
                             {
                                 Nombre = (String?)p["nombre"],
                                 Puesto = (string?)p["puesto"],
                                 Foto = (String?)p["foto"]
                             };
            foreach (Personaje p in personajes) { Personajes.Add(p); }
        }
    }

}
