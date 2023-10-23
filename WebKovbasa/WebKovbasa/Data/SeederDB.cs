using Microsoft.EntityFrameworkCore;
using WebKovbasa.Data.Entities;

namespace WebKovbasa.Data
{
    public static class SeederDB
    {
        public static void SeedData(this IApplicationBuilder app)
        {
            using (var scope = app.ApplicationServices
                    .GetRequiredService<IServiceScopeFactory>().CreateScope())
            {
                var context = scope.ServiceProvider.GetRequiredService<AppEFContext>();
                context.Database.Migrate();
                if (!context.Categories.Any())
                {
                    var laptop = new CategoryEntity
                    {
                        Name = "Ноутбуки",
                        Image = "https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE4OXzi?ver=3a58",
                        Description = "Для роботи і навчання",
                        DateCreated = DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc)
                    };
                    var clothes = new CategoryEntity
                    {
                        Name = "Одяг",
                        Image = "https://static.independent.co.uk/s3fs-public/thumbnails/image/2019/03/27/11/woman-clothing-happy.jpg?width=1200",
                        Description = "Для дівчат і хлопців",
                        DateCreated = DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc)
                    };
                    context.Categories.Add(laptop);
                    context.Categories.Add(clothes);
                    context.SaveChanges();
                }
            }
        }
    }
}
