//entity
import javax.persitence.Entity;
import javax.persitence.Id;
import javax.persitence.Cacheable;


@Entity
@Chachable
@Chache(Usage=ChacheConcarancyStratagy.READ_WRITE)
public class Id
{
  @Id 
  int id;
  int subject_id;
  int test_id;
}
