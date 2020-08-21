/**
 * 
 */
package in.parteek.beans;

import lombok.*;
import javax.persistence.*;

/**
 * Created on : 2019-01-31, 8:17:52 a.m.
 *
 * @author Parteek Dheri
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@NamedQuery(name = "Student.byname", query = "from Student where name = :name")
public class Student {
	private String name;
	private String program;
	@Id
	@GeneratedValue
	private int student_id;

	public Student(String name, String program) {
		this.name = name;
		this.program = program;
	}

}
