package cz.cvut.fel.pjv.GUI;

import com.google.common.primitives.Ints;
import cz.cvut.fel.pjv.GUI.PGN.Writer;
import cz.cvut.fel.pjv.engine.Colour;
import cz.cvut.fel.pjv.engine.board.moves.Move;
import cz.cvut.fel.pjv.engine.board.moves.MoveType;
import cz.cvut.fel.pjv.engine.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TakenPiecesPanel extends JPanel {

    private static final Color PANEL_COLOUR = Color.decode("0xFDF5E6");
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(50,80);
    private final JPanel northPanel;
    private final JPanel southPanel;
    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);
    private final static Logger LOGGER = Logger.getLogger(Writer.class.getSimpleName());


    TakenPiecesPanel() {
        super(new BorderLayout());
        this.setBackground(PANEL_COLOUR);
        this.setBorder(PANEL_BORDER);
        this.northPanel = new JPanel(new GridLayout(8,2));
        this.southPanel = new JPanel(new GridLayout(8,2));
        this.northPanel.setBackground(PANEL_COLOUR);
        this.southPanel.setBackground(PANEL_COLOUR);
        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.southPanel, BorderLayout.SOUTH);
        this.setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void redo(final MoveLog moveLog) {
        this.northPanel.removeAll();
        this.southPanel.removeAll();
        final List<Piece> whiteTakenPieces = new ArrayList<>();
        final List<Piece> blackTakenPieces = new ArrayList<>();
//      gets attacked piece from move from move log
        for(final Move move : moveLog.getMoves()){
            if (move.getMoveType() == MoveType.ATTACK) {
                final Piece takenPiece = move.getAttackedPiece();
                if (takenPiece.getPieceColour() == Colour.WHITE) {
                    whiteTakenPieces.add(takenPiece);
                } else if (takenPiece.getPieceColour() == Colour.BLACK) {
                    blackTakenPieces.add(takenPiece);
                } else {
                    throw new RuntimeException("Should not reach here!");
                }
            }
        }
//        sort pieces depending on their value
        whiteTakenPieces.sort((piece, piece2) -> Ints.compare(piece.getPieceValue(),
                piece2.getPieceValue()));

        blackTakenPieces.sort((piece, piece2) -> Ints.compare(piece.getPieceValue(),
                piece2.getPieceValue()));
//      adds images for each piece
        addImages(whiteTakenPieces);
        addImages(blackTakenPieces);
        validate();
    }
//    adds images of piece to the panel depending on their piece type
    private void addImages(Collection<Piece> pieces) {
        for (final Piece takenPiece : pieces) {
            try {
                //                images are save in pattern- for instance white bishop = WB.png
                final BufferedImage image = ImageIO.read(new File("images/pieces/"
                        + takenPiece.getPieceColour().toString().substring(0,1)
                        + takenPiece.getPieceType().toString()
                        + ".png"));
                this.southPanel.add(new JLabel(new ImageIcon(image)));
            } catch (final IOException e) {
                LOGGER.log(Level.INFO, "Unexpected error while displaying " +
                        "taken piece icon!");
            }
        }
    }
}
