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
