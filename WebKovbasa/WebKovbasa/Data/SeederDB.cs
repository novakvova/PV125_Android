using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using WebKovbasa.Constants;
using WebKovbasa.Data.Entities;
using WebKovbasa.Data.Entities.Identity;

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

                var userManager = scope.ServiceProvider
                    .GetRequiredService<UserManager<UserEntity>>();

                var roleManager = scope.ServiceProvider
                    .GetRequiredService<RoleManager<RoleEntity>>();

                if (!context.Roles.Any())
                {
                    RoleEntity admin = new RoleEntity
                    {
                        Name = Roles.Admin,
                    };
                    RoleEntity user = new RoleEntity
                    {
                        Name = Roles.User,
                    };
                    var result = roleManager.CreateAsync(admin).Result;
                    result = roleManager.CreateAsync(user).Result;
                }

                if (!context.Users.Any())
                {
                    UserEntity user = new UserEntity
                    {
                        FirstName = "Марко",
                        LastName = "Муха",
                        Email = "muxa@gmail.com",
                        UserName = "muxa@gmail.com",
                    };
                    var result = userManager.CreateAsync(user, "123456")
                        .Result;
                    if (result.Succeeded)
                    {
                        result = userManager
                            .AddToRoleAsync(user, Roles.Admin)
                            .Result;
                    }
                }


                if (!context.Categories.Any())
                {
                    var laptop = new CategoryEntity
                    {
                        Name = "Ноутбуки",
                        Image = "https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE4OXzi?ver=3a58",
                        Description = "Для роботи і навчання",
                        DateCreated = DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc),
                        UserId=1
                    };
                    var clothes = new CategoryEntity
                    {
                        Name = "Одяг",
                        Image = "https://static.independent.co.uk/s3fs-public/thumbnails/image/2019/03/27/11/woman-clothing-happy.jpg?width=1200",
                        Description = "Для дівчат і хлопців",
                        DateCreated = DateTime.SpecifyKind(DateTime.Now, DateTimeKind.Utc),
                        UserId = 1
                    };
                    context.Categories.Add(laptop);
                    context.Categories.Add(clothes);
                    context.SaveChanges();
                }
            }
        }
    }
}
