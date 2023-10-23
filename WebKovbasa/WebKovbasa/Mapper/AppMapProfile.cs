using AutoMapper;
using WebKovbasa.Data.Entities;
using WebKovbasa.Models.Category;

namespace WebKovbasa.Mapper
{
    public class AppMapProfile : Profile
    {
        public AppMapProfile()
        {
            CreateMap<CategoryEntity, CategoryItemViewModel>();
            CreateMap<CategoryCreateViewModel, CategoryEntity>();
        }
    }
}
