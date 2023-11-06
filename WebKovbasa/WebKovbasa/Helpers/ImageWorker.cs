using SixLabors.ImageSharp.Formats.Webp;

namespace WebKovbasa.Helpers
{
    public static class ImageWorker
    {
        public static string SaveImage(string imageBase64)
        {
            string fileName = Path.GetRandomFileName() + ".webp";
            try
            {
                string base64 = imageBase64;
                if (base64.Contains(","))
                    base64 = base64.Split(',')[1];

                byte[] byteBuffer = Convert.FromBase64String(base64);


                string dirSave = Path.Combine(Directory.GetCurrentDirectory(), "images", fileName);

                using (var image = Image.Load(byteBuffer))
                {
                    image.Mutate(x =>
                    {
                        x.Resize(new ResizeOptions
                        {
                            Size = new Size(1200, 1200),
                            Mode = ResizeMode.Max
                        });
                    });
                    using (var ms = new MemoryStream())
                    {
                        image.Save(ms, new WebpEncoder());
                        System.IO.File.WriteAllBytes(dirSave, ms.ToArray());
                    }
                }
            }
            catch (Exception ex)
            {
                throw new Exception("Файл не вдалося зберегти!" + ex.Message);
            }
            return fileName;
        }

        public static void RemoveImage(string fileName)
        {
            try
            {
                string file = Path.Combine(Directory.GetCurrentDirectory(), "images", fileName);
                if (System.IO.File.Exists(file))
                {
                    System.IO.File.Delete(file);
                }
            }
            catch
            { }
        }
    }
}
