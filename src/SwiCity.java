import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SwiCity implements ItemListener, ActionListener, ListSelectionListener, ChangeListener {
    JFrame win, winSecond;
    JCheckBox box, boxSecond;
    JButton ok, rock, scissors, paper, okSecond, reset;
    JLabel stick, invPas, res, player, comp, plCo, text, textTo;
    JPasswordField pasWord;
    String[] ar = {"Камень, Ножницы, Бумага"};
    JScrollPane scroll;
    JList<String> list;
    Icon iconF, iconS, iconT, rk, ss, pr, winner, loser, winLr;
    Icon[] arr;
    BoundedRangeModel model;
    JSlider jsl;
    int a, b, check, par, value, sch, bag;


    SwiCity() {

        win = new JFrame("Games");
        winSecond = new JFrame("Games");
        win.setSize(250, 230);
        win.setLocation(850, 450);
        winSecond.setLocation(850, 450);
        win.setResizable(false);
        winSecond.setSize(255, 250);
        winSecond.setResizable(false);
        win.setLayout(new FlowLayout());
        winSecond.setLayout(new FlowLayout());
        stick = new JLabel("Выберете уровень доступа: ");
        invPas = new JLabel("");
        player = new JLabel("");
        comp = new JLabel("");
        plCo = new JLabel("");
        text = new JLabel("");
        textTo = new JLabel("");
        res = new JLabel();
        win.add(stick);
        box = new JCheckBox("Admin");
        boxSecond = new JCheckBox("User");
        win.add(box);
        win.add(boxSecond);
        iconF = new ImageIcon("picOne.png");
        iconS = new ImageIcon("picTwo.png");
        iconT = new ImageIcon("picThree.png");
        rk = new ImageIcon("rk.png");
        ss = new ImageIcon("ss.png");
        pr = new ImageIcon("pr.png");
        winner = new ImageIcon("winner.gif");
        loser = new ImageIcon("loser.gif");
        winLr = new ImageIcon("winLr.gif");
        ok = new JButton("OK");
        reset = new JButton("Новая игра");
        okSecond = new JButton("OK");
        model = new DefaultBoundedRangeModel(1, 0, 1, 100);
        jsl = new JSlider(model);
        rock = new JButton(iconF);
        scissors = new JButton(iconS);
        paper = new JButton(iconT);
        win.add(ok);
        ok.setActionCommand("");
        pasWord = new JPasswordField(11);
        list = new JList<>(ar);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(200, 150));
        list.addListSelectionListener(this);
        winSecond.add(scroll);


        rock.addActionListener(this);
        scissors.addActionListener(this);
        paper.addActionListener(this);
        box.addItemListener(this);
        boxSecond.addItemListener(this);
        jsl.addChangeListener(this);
        reset.addActionListener((et)->{
            if (et.getActionCommand().equals("Новая игра")) {
                win.dispose();
                winSecond.dispose();
                SwingUtilities.invokeLater(SwiCity::new);
            }
        });
        win.add(res);
        arr = new Icon[]{rk, ss, pr};
        jsl.setMajorTickSpacing(99);
        jsl.setMinorTickSpacing(10);
        jsl.setPaintLabels(true);
        jsl.setPaintTicks(true);


        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winSecond.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (((JCheckBox) e.getItem()).isSelected()) {
            ok.setActionCommand("OK");
            ok.addActionListener(this);
        } else {
            ok.setActionCommand("");
            win.remove(pasWord);
        }
        win.repaint();
        if (box.isSelected()) {
            boxSecond.setEnabled(false);
            res.setVisible(true);
            pasWord.setVisible(true);
        } else if (boxSecond.isSelected()) {
            box.setEnabled(false);
            pasWord.setVisible(false);
            res.setVisible(false);
        } else {
            box.setEnabled(true);
            boxSecond.setEnabled(true);
            res.setText("");


        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        par = (int) (Math.random() * 3);
        if (e.getActionCommand().equals("OK") & box.isSelected()) {
            res.setText("Введите пароль администратора");
            win.add(pasWord);
            if (pasWord.getText().equals("111")) {
                win.dispose();
                winSecond.setVisible(true);
                sch = 0;
            } else if (!pasWord.getText().equals("111") & !pasWord.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Неверный пароль. Доступ запрещен!");
                win.dispose();
                SwingUtilities.invokeLater(SwiCity::new);

            }

        } else if (e.getActionCommand().equals("OK") & boxSecond.isSelected()) {
            win.dispose();
            winSecond.setVisible(true);
        }
        if (box.isSelected()) {
            if (e.getActionCommand().equals("rock")) {
                check = 0;
                player.setIcon(rk);
                comp.setIcon(ss);
            } else if (e.getActionCommand().equals("scissors")) {
                check = 1;
                player.setIcon(ss);
                comp.setIcon(pr);
            } else if (e.getActionCommand().equals("paper")) {
                check = 2;
                player.setIcon(pr);
                comp.setIcon(rk);
            }
        }
        if (boxSecond.isSelected()) {
            if (e.getActionCommand().equals("rock")) {
                check = 0;
                player.setIcon(rk);
                comp.setIcon(arr[par]);
            } else if (e.getActionCommand().equals("scissors")) {
                check = 1;
                player.setIcon(ss);
                comp.setIcon(arr[par]);
            } else if (e.getActionCommand().equals("paper")) {
                check = 2;
                player.setIcon(pr);
                comp.setIcon(arr[par]);
            }

        }

        if (check == 0 && par == 1 || (check == 1 && par == 2) || (check == 2 && par == 0) || box.isSelected()) {

            a++;
            text.setText("                   Вы победили                   ");
            textTo.setText("             Счет: " + a + ":" + b + "          ");
            sch++;

        } else if ((check == 0 && par == 2) || (check == 1 && par == 0) || (check == 2 && par == 1)) {

            b++;
            text.setText("               Победил компьютер             ");
            textTo.setText("             Счет: " + a + ":" + b + "          ");
            sch++;
        } else {

            text.setText("                     Ничья                      ");
            textTo.setText("            Счет: " + a + ":" + b + "           ");
            sch++;
        }


        if (a > b & (bag == sch || sch > value & sch > 1)) {
            rock.setVisible(false);
            scissors.setVisible(false);
            textTo.setVisible(false);
            paper.setVisible(false);
            comp.setVisible(false);
            text.setText("               ВЫ ПОБЕДИЛИ. СЧЕТ *" + a + ":" + b + "*               ");
            player.setIcon(winner);
            reset.setVisible(true);
            winSecond.setSize(355, 380);
        } else if (a < b & (bag == sch || sch > value & sch > 1)) {
            rock.setVisible(false);
            scissors.setVisible(false);
            textTo.setVisible(false);
            paper.setVisible(false);
            player.setVisible(false);
            text.setText("         ПОБЕДИЛ КОМПЬЮТЕР. СЧЕТ *" + a + ":" + b + "*         ");
            comp.setIcon(loser);
            reset.setVisible(true);
            winSecond.setSize(355, 380);
        } else if (a == b & (bag == sch || sch > value & sch > 1)) {
            rock.setVisible(false);
            scissors.setVisible(false);
            textTo.setVisible(false);
            paper.setVisible(false);
            comp.setVisible(false);
            player.setVisible(false);
            text.setText("                    НИЧЬЯ. СЧЕТ *" + a + ":" + b + "*                       ");
            winSecond.add(plCo);
            plCo.setIcon(winLr);
            reset.setVisible(true);
            winSecond.setSize(355, 380);
        }
        win.revalidate();
        win.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (list.isSelectedIndex(0)) {
            scroll.setVisible(false);
            rock.setActionCommand("rock");
            scissors.setActionCommand("scissors");
            paper.setActionCommand("paper");
            a = 0;
            b = 0;
            winSecond.add(stick);
            stick.setText("Выберите количество партий");
            winSecond.add(jsl);
            winSecond.add(okSecond);
            okSecond.addActionListener(e1 -> {
                if (e1.getActionCommand().equals("OK")) {
                    stick.setText("");
                    winSecond.add(rock);
                    winSecond.add(scissors);
                    winSecond.add(paper);
                    winSecond.add(text);
                    winSecond.add(reset);
                    reset.setVisible(false);

                    text.setText("");
                    winSecond.add(player);
                    winSecond.add(comp);
                    winSecond.add(textTo);
                    textTo.setText("");
                    winSecond.repaint();
                    jsl.setVisible(false);
                    okSecond.setVisible(false);
                }
                winSecond.revalidate();
            });
        }
        winSecond.revalidate();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        winSecond.add(textTo);
        value = ((JSlider) e.getSource()).getValue();
        textTo.setText(" " + value);
        if (boxSecond.isSelected()) {
            bag = value + 1;
        } else {
            bag = value + 2;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwiCity::new);
    }
}

