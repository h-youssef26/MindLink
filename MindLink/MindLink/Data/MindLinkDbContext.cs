using Microsoft.EntityFrameworkCore;
using MindLink.Models;
namespace MindLink.Data
{
    public class MindLinkDbContext : DbContext
    {
        public MindLinkDbContext(DbContextOptions<MindLinkDbContext> options) : base(options)
        {
        }
        public DbSet<Post> Posts { get; set; }
        
    }
    
}
