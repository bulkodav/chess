package cz.cvut.fel.pjv.GUI;
import cz.cvut.fel.pjv.engine.board.Board;
import cz.cvut.fel.pjv.engine.board.moves.Move;

import javax.swing.*;
import java.awt.*;

public class GameHistoryPanel extends JPanel {

    private static final Dimension HISTORY_PANEL_DIMENSION = new Dimension(150,400);
    private final DataModel model;
    private final JScrollPane jScrollPane;
    private final Clock clock;

    GameHistoryPanel() {
        this.setLayout(new BorderLayout());
        this.model = new DataModel();
        this.clock = new Clock();
        final JTable table = new JTable(this.model);
        table.setRowHeight(15);
        this.jScrollPane = new JScrollPane(table);
        jScrollPane.setColumnHeaderView(table.getTableHeader());
        jScrollPane.setPreferredSize(HISTORY_PANEL_DIMENSION);
        this.add(jScrollPane, BorderLayout.CENTER);
        this.add(clock, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void redo(final Board board,
              final MoveLog moveHistory) {
        int currentRow = 0;
        this.model.clear();
        for (final Move move: moveHistory.getMoves()) {
            final String moveText = move.getPerformedToString();
            if (move.getMovedPiece().getPieceColour().isWhite()) {
                this.model.setValueAt(moveText, currentRow, 0);
            } else if (move.getMovedPiece().getPieceColour().isBlack()) {
                this.model.setValueAt(moveText, currentRow, 1);
                currentRow++;
            }
        }
        if (moveHistory.getMoves().size() > 0) {
            final Move lastMove = moveHistory.getMoves().get(moveHistory.size() - 1);
            final String moveText = lastMove.getPerformedToString();

            if (lastMove.getMovedPiece().getPieceColour().isWhite()) {
                this.model.setValueAt(moveText + calculateCheckAndCheckMateHash(board), currentRow, 0);
            } else if (lastMove.getMovedPiece().getPieceColour().isBlack()) {
                this.model.setValueAt(moveText + calculateCheckAndCheckMateHash(board), currentRow - 1, 1);
            }
        }
        final JScrollBar vertical = jScrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    private String calculateCheckAndCheckMateHash(final Board board) {
        if (board.getCurrentPlayer().isInCheckMate()) {
            return "#";
        } else if (board.getCurrentPlayer().isInCheck()) {
            return "+";
        }
        return "";
    }

    public Clock getClock() {
        return clock;
    }
}
