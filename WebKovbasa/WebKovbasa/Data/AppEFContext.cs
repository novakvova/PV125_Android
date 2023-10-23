using Microsoft.EntityFrameworkCore;
using WebKovbasa.Data.Entities;

namespace WebKovbasa.Data
{
    public class AppEFContext : DbContext
    {
        public AppEFContext(DbContextOptions<AppEFContext> options)
            : base(options) { }

        public DbSet<CategoryEntity> Categories { get; set; }
    }
}
