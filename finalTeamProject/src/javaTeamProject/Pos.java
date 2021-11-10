package javaTeamProject;

import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Pos extends JPanel{
	
// 메뉴 버튼 생성 총 18개의 버튼 생성 추가로 항목생성을 원한다면 이부분을 수정하면 된다.
   JButton[] MBtn = new JButton[18];
   //Array 클래스를 생성자로 mu라는 변수에 호출
   Array mu = new Array();
   
   //텍스트 필드 선언. 총 결제량 부분
   JTextField tf = new JTextField(30);
   //서브버튼 총 4개 선언 추가 기능을 원하면 이부분 수정하면된다.
   JButton[] SBtn = new JButton[4];
   // model 생성자 선언후 태이블에 적용
   DefaultTableModel model = new DefaultTableModel(mu.Data,mu.ColName);
   // table이라는 변수사용하여 JTable 생성
   JTable table = new JTable(model);
   
   //태이블 화면을 위한 클래스
   class Screen extends JPanel{
      Screen(){
         //배경색은 레몬옐로우
    	  setBackground(new Color(255,250,205));
         //최대줄은 50줄까지
         table.setRowHeight(50);
         // JScrollPane 형식의 table 생성
         add(new JScrollPane(table));
      }
   }
   //메뉴버튼 클래스
   class MenuBtn extends JPanel{
      MenuBtn(){
    	  //메뉴버튼의 배치관리자는 grid
         setLayout(new GridLayout(6,3,3,3));
         //메뉴바의 배경을 가독성을 위해 레몬옐로우보다 더 밝은 색 사용
         setBackground(new Color(255,255,224));
         // 유지 보수할때 일일이 메뉴버튼 클래스를 수정하지않고 위의 배열만 손보면 늘어나도록 for문과 length를 활용
         for(int i=0;i<MBtn.length;i++) {
            MBtn[i]= new JButton(mu.menu[i]);
            //늘어난 만큼 생성및 추가
            add(MBtn[i]);
         }
      }
   }
   //서브버튼 클래스
   class StrBtn extends JPanel{
      StrBtn(){
    	//메뉴바의 배경을 가독성을 위해 레몬옐로우보다 더 밝은 색 사용
          setBackground(new Color(255,255,224));
         // 배치관리자는 메뉴와 마찬가지로 Grid 채택
         setLayout(new GridLayout(1,4,3,3));
         // 메뉴버튼과 마찬가지로 유지보수 용이를 위해 for문 과 length 사용
         for(int i=0;i<mu.Str.length;i++) {
            SBtn[i]= new JButton(mu.Str[i]);
            //생성 및 추가
            add(SBtn[i]);
         }
      }
   }
   //Pos 클래스의 생성자. 여기서부터 배치를 하기 시작한다.
   public Pos() {
	  // 내가 원하는 위치에 지정하기위해 배치관리자 null로 지정
      setLayout(null);
      //배경색은 레몬옐로우
      setBackground(new Color(255,250,205));
      //메뉴버튼, 서브버튼, table 스크린 클래스들을 각각 생성자를 이용해 호출.
      MenuBtn mbtn = new MenuBtn();
      StrBtn sbtn = new StrBtn();
      Screen sc = new Screen();
      
      //먼저 계산결과가 나오는 textfield 위치 조정
      tf.setSize(460, 70);
      tf.setLocation(540,480);
      add(tf);
      // table 의 위치 조정
      sc.setSize(500, 500);
      sc.setLocation(520,20);
      add(sc);
      // 메인메뉴의 위치 조정
      mbtn.setSize(450, 430);
      mbtn.setLocation(25,20);
      add(mbtn);
      //서브메뉴의 위치 조정
      sbtn.setSize(450, 70);
      sbtn.setLocation(25, 480);
      add(sbtn);
      
      //유지보수를 위해 메인메뉴를 for문과 length로 늘렸기때문에 각각의 버튼에 엑션리쓰너 적용
      
      
      //1번 메뉴버튼
      for(int i=0;i<MBtn.length;i++) {
         final int index =i;
         //action listener 선언  + 자바에서 제공하는 이벤트 리쓰너 인터페이스를 받아온다
         MBtn[i].addActionListener(new ActionListener() {
        	 //actionPerformed 오버라이딩
            public void actionPerformed(ActionEvent e) {
            	// 메인메뉴버튼에서부터 정보를 가져온다
               JButton MBtn = (JButton)e.getSource();
               // 이를 디폴트테이블 모델로 지정한 m이라는 변수에 지정한다음에
               DefaultTableModel m = (DefaultTableModel)table.getModel();
               // 여기에 제품을 선택할때마다 위에서의 각각의 1차원 배열 menu와 price를 불러와 줄에 추가시킨다.
               m.addRow(new Object[]{mu.item[index],mu.menu[index],mu.count[index],mu.price[index]});
               //이 값이 있다면. 즉 table에 올라와 있다면.
               if(mu.menu[index] != null) {
                   //수량을 추가하고.
                   mu.count[index]++;
                   // 전것을 지운다.
                   m.removeRow(table.getSelectedRow());
                }         
            }
         });
      }

      //1번째 서브버튼은 해당 열을 선택하고 누르면 가격이 0이 되도록 변경하도록 설계 // 긴급재난금
      SBtn[0].addActionListener(new ActionListener() {
         // 눌렀을때 액션이 나오도록 선언 + 자바에서 제공하는 이벤트 리쓰너 인터페이스를 받아온다
    	//actionPerformed 오버라이딩
         public void actionPerformed(ActionEvent e) {
        	// 메뉴버튼으로 받아온 정보를 받아온다 
            JButton MBtn = (JButton)e.getSource();
            // setValueAt의 문법은 Object aValue,int row,int column) 순이다.
            // 즉 값은 0으로 변경할 것이고. table에서 선택한 줄을 지정할 것이고 , 위에서 폼목,수량,가격  즉 0번부터 시작이니 가격은 3번
            table.setValueAt(0, table.getSelectedRow(), 3);
         }
      });
         // 두번째 서브버튼은 table에서 선택한 열 삭제이다. + 자바에서 제공하는 이벤트 리쓰너 인터페이스를 받아온다
      SBtn[1].addActionListener(new ActionListener() {
    	  // 눌렀을때 액션이 나오도록 선언  + 자바에서 제공하는 이벤트 리쓰너 인터페이스를 받아온다
    	//actionPerformed 오버라이딩
         public void actionPerformed(ActionEvent e) {
        	//메뉴버튼으로 받아온 정보를 받아온다
            JButton MBtn = (JButton)e.getSource();
         // 이를 디폴트테이블 모델로 지정한 m이라는 변수에 지정한다음에
            DefaultTableModel m = (DefaultTableModel)table.getModel();
            // m을 이용해서 removeRow이용해서 간단하게 삭제.
            m.removeRow(table.getSelectedRow());
            //테이블의 수량을 1로 변경한다.
            Arrays.fill(mu.count,1);
         }
      });

      // 세번째 서브버튼은 전체삭제 버튼이다.
      SBtn[2].addActionListener(new ActionListener() {
    	  // 눌렀을때 액션이 나오도록 선언 + 자바에서 제공하는 이벤트 리쓰너 인터페이스를 받아온다
    	//actionPerformed 오버라이딩
         public void actionPerformed(ActionEvent e) {
        	//메뉴버튼으로 받아온 정보를 받아온다
            JButton MBtn = (JButton)e.getSource();
         // 이를 디폴트테이블 모델로 지정한 m이라는 변수에 지정한다음에
            DefaultTableModel m = (DefaultTableModel)table.getModel();
            // m을 이용해서 모든 줄의 count값을 0으로 지정한다.
            m.setRowCount(0);
            //또한 텍스트필드에 값이 남아있는경우도 있으니 이를 ""여백으로 리셋시켜준다.
            tf.setText(String.valueOf(""));
            // 테이블의 수량을 1로 만든다.
            Arrays.fill(mu.count,1);
            
         }
      });
      
      //4번째 서브버튼은 결제버튼이다 즉 계산이 들어가야 한다.
      SBtn[3].addActionListener(new ActionListener() {
    	  // 눌렀을때 액션이 나오도록 선언 + 자바에서 제공하는 이벤트 리쓰너 인터페이스를 받아온다
         public void actionPerformed(ActionEvent e) {
        	//메뉴버튼으로 받아온 정보를 받아온다
            JButton MBtn = (JButton)e.getSource();
            //rowCont 라는 정수형 변수에 table에 지정되어있는 줄의 수를 가져온다
            int rowCont = table.getRowCount();
            // sum이라는 정수형 변수를 0으로 초기화한다.
            int sum =0;
            // for문을 이용해 테이블에 있는 모든 줄을 계산할 때까지 증감식으로 덧셈하겠다는 구문
            for(int i=0;i<rowCont;i++) {
            	// sum = sum + 정수형으로 table의 각각의 열에 price 컬럼은 위에서 말했듯이 2번컬럼에 지정된 값을 덧셈.
            	// 수량
            	int firstsum = (int)table.getValueAt(i, 2);
            	// 가격
            	int secondsum = (int)table.getValueAt(i, 3);
            	int all = firstsum * secondsum;
            	//이를 sum에 계속 저장한다.
            	sum += all;
            }
            //sum을 setText를 이용해서 text필드에 보여준다
            tf.setText(String.valueOf("총 결제 금액 : "+sum));
            //이때 폰트의 크기는 두껍고 40폰트.
            tf.setFont(new Font("가격", Font.BOLD, 40));
            // 색은 
            tf.setForeground(new Color(210,105,30) );
            
         }
      });
   }
}