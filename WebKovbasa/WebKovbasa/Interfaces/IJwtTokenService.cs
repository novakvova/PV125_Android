using WebKovbasa.Data.Entities.Identity;

namespace WebKovbasa.Interfaces
{
    public interface IJwtTokenService
    {
        Task<string> CreateToken(UserEntity user);
    }
}
